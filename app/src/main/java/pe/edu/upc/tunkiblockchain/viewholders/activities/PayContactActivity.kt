package pe.edu.upc.tunkiblockchain.viewholders.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.github.ybq.android.spinkit.style.CubeGrid
import pe.edu.upc.tunkiblockchain.R
import pe.edu.upc.tunkiblockchain.models.Client
import pe.edu.upc.tunkiblockchain.models.TradeCoins
import pe.edu.upc.tunkiblockchain.repository.RetrofitRepository
import pe.edu.upc.tunkiblockchain.repository.TradeCoinsRepository
import pe.edu.upc.tunkiblockchain.utils.LoadingActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PayContactActivity : AppCompatActivity() {

    private lateinit var contactNameTextView: TextView
    private lateinit var amountEditText: EditText
    private lateinit var payFAB: FloatingActionButton
    private lateinit var loadingAnimation: ProgressBar
    private lateinit var loadingtv: TextView
    private var retrofit = RetrofitRepository().getRetrofitInstance()
    private var tradeCoinsRepo =retrofit.create(TradeCoinsRepository::class.java)
    private var tradeCoins = TradeCoins()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_contact)

        val sharedPref = applicationContext.getSharedPreferences("BlockChainPreferences",Context.MODE_PRIVATE)

        val contact = intent.extras.getSerializable("contact") as Client
        val currentUser = getSharedPreferences("BlockChainPreferences", Context.MODE_PRIVATE).getString("userId","DefUserId")
        if(currentUser=="defUserId")
        {
            Toast.makeText(this,"Could not read current user data",Toast.LENGTH_SHORT).show()
            finish()
        }
        contactNameTextView = findViewById(R.id.tvContactNamePay)
        amountEditText = findViewById(R.id.etAmount)
        payFAB = findViewById(R.id.fabPay)
        loadingtv = findViewById(R.id.tvLoading)
        loadingAnimation = findViewById(R.id.loadingAnimationP2P)
        loadingAnimation.setIndeterminateDrawableTiled(CubeGrid())

        contactNameTextView.text = contact.clientName

        payFAB.setOnClickListener {
            tradeCoins.amount = amountEditText.text.toString().toDouble()
            tradeCoins.clientFrom = "org.tunki.network.Client#$currentUser"
            tradeCoins.clientTo = "org.tunki.network.Client#${contact.clientId}"
            //loadingAnimation.visibility = View.VISIBLE
            //loadingtv.visibility = View.VISIBLE
            tradeCoinsRepo.postTradeCoins(tradeCoins,sharedPref.getString("api_key","api_key")as String).enqueue(object: Callback<TradeCoins>{
                override fun onResponse(call: Call<TradeCoins>, response: Response<TradeCoins>) {
                    //loadingAnimation.visibility = View.GONE
                    //loadingtv.visibility = View.GONE
                    Toast.makeText(this@PayContactActivity,"Payment done to contact ${contact.clientName} successfully!",Toast.LENGTH_SHORT).show()
                    finish()
                }

                override fun onFailure(call: Call<TradeCoins>, t: Throwable) {
                    //loadingAnimation.visibility = View.GONE
                    //loadingtv.visibility = View.GONE
                    Toast.makeText(this@PayContactActivity,"There was an error with the payment, please try again",Toast.LENGTH_SHORT).show()
                    Log.d("Debug","Could not post trade transaction",t)
                }
            })
            it.context.startActivity(Intent(it.context, LoadingActivity::class.java))

        }
    }
}
