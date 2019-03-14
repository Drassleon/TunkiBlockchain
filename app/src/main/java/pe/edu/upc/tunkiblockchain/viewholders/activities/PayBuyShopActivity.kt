package pe.edu.upc.tunkiblockchain.viewholders.activities

import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import pe.edu.upc.tunkiblockchain.R
import pe.edu.upc.tunkiblockchain.models.BuyCoins
import pe.edu.upc.tunkiblockchain.models.CoinProvider
import pe.edu.upc.tunkiblockchain.models.SellCoins
import pe.edu.upc.tunkiblockchain.repository.BuyCoinsRepository
import pe.edu.upc.tunkiblockchain.repository.RetrofitRepository
import pe.edu.upc.tunkiblockchain.repository.SellCoinsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PayBuyShopActivity : AppCompatActivity() {

    private val buyCoinsRepository = RetrofitRepository().getRetrofitInstance().create(BuyCoinsRepository::class.java)
    private val sellCoinsRepository = RetrofitRepository().getRetrofitInstance().create(SellCoinsRepository::class.java)

    private lateinit var shopNameTextView: TextView
    private lateinit var buyFab: FloatingActionButton
    private lateinit var sellFab: FloatingActionButton
    private lateinit var amountTextView: TextView
    private lateinit var tokenAmountTextView: TextView
    private lateinit var loadingAnimation: ProgressBar
    private lateinit var loadingTextView: TextView
    private var buyCoins = BuyCoins()
    private var sellCoins = SellCoins()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_buy_shop)
        val currentUser = getSharedPreferences("BlockChainPreferences", Context.MODE_PRIVATE).getString("userId","DefUserId") as String

        val shop = intent.getSerializableExtra("shop") as CoinProvider

        shopNameTextView = findViewById(R.id.tvShopNameTransaction)
        buyFab = findViewById(R.id.fabBuy)
        sellFab = findViewById(R.id.fabSell)
        amountTextView = findViewById(R.id.etAmountShopDetails)
        tokenAmountTextView = findViewById(R.id.tvAmountTokensShop)
        loadingAnimation = findViewById(R.id.loadingAnimationP2C)
        loadingTextView = findViewById(R.id.tvLoadingP2C)

        shopNameTextView.text = shop.coinProviderName
        tokenAmountTextView.text = getSharedPreferences("BlockChainPreferences",Context.MODE_PRIVATE).getString("amountTokens","DefTokenValue") as String
        buyFab.setOnClickListener {
            buyCoins.amount = amountTextView.text.toString().toDouble()
            buyCoins.client = "org.tunki.network.Client#$currentUser"
            buyCoins.shop = "${shop.`$class`}#${shop.coinProviderId}"
            buyCoins.exchangeRate = 1.0
            loadingAnimation.visibility = View.VISIBLE
            loadingTextView.visibility = View.VISIBLE
            buyCoinsRepository.postBuyCoinTransaction(buyCoins).enqueue(object: Callback<BuyCoins>{
                override fun onResponse(call: Call<BuyCoins>, response: Response<BuyCoins>) {
                    loadingAnimation.visibility = View.GONE
                    loadingTextView.visibility = View.GONE
                    Toast.makeText(this@PayBuyShopActivity,"Purchase Successful",Toast.LENGTH_SHORT).show()
                    Log.d("Debug","Purchase Successful")
                    finish()
                }

                override fun onFailure(call: Call<BuyCoins>, t: Throwable) {
                    loadingAnimation.visibility = View.GONE
                    loadingTextView.visibility = View.GONE
                    Toast.makeText(this@PayBuyShopActivity,"Purchase failed!",Toast.LENGTH_SHORT).show()
                    Log.d("Debug","Purchase Failed!",t)
                }
            })
        }
        sellFab.setOnClickListener {
            sellCoins.amount = amountTextView.text.toString().toDouble()
            sellCoins.client = "org.tunki.network.Client#$currentUser"
            sellCoins.exchangeRate = 1.0
            sellCoins.shop = "${shop.`$class`}#${shop.coinProviderId}"
            loadingAnimation.visibility = View.VISIBLE
            loadingTextView.visibility = View.VISIBLE
            sellCoinsRepository.postSellCoinsTransaction(sellCoins).enqueue(object: Callback<SellCoins>{
                override fun onResponse(call: Call<SellCoins>, response: Response<SellCoins>) {
                    loadingAnimation.visibility = View.GONE
                    loadingTextView.visibility = View.GONE
                    Toast.makeText(this@PayBuyShopActivity,"Sale Successful",Toast.LENGTH_SHORT).show()
                    Log.d("Debug","Sale Successful")
                    finish()
                }

                override fun onFailure(call: Call<SellCoins>, t: Throwable) {
                    loadingAnimation.visibility = View.GONE
                    loadingTextView.visibility = View.GONE
                    Toast.makeText(this@PayBuyShopActivity,"Sale Failed!",Toast.LENGTH_SHORT).show()
                    Log.d("Debug","Sale Failed!",t)
                }
            })
        }
    }
}
