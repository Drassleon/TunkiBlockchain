package pe.edu.upc.tunkiblockchain.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import pe.edu.upc.tunkiblockchain.R
import pe.edu.upc.tunkiblockchain.adapter.BuyTransactionAdapter
import pe.edu.upc.tunkiblockchain.adapter.SellTransactionAdapter
import pe.edu.upc.tunkiblockchain.adapter.TradeTransactionAdapter
import pe.edu.upc.tunkiblockchain.models.BuyCoins
import pe.edu.upc.tunkiblockchain.models.SellCoins
import pe.edu.upc.tunkiblockchain.models.TradeCoins
import pe.edu.upc.tunkiblockchain.repository.BuyCoinsRepository
import pe.edu.upc.tunkiblockchain.repository.RetrofitRepository
import pe.edu.upc.tunkiblockchain.repository.SellCoinsRepository
import pe.edu.upc.tunkiblockchain.repository.TradeCoinsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionsFragment : Fragment() {


    private lateinit var tradeRecyclerView: RecyclerView
    private lateinit var tradeAdapter: TradeTransactionAdapter
    private lateinit var tradeLayoutManager: RecyclerView.LayoutManager

    private lateinit var buyRecyclerView: RecyclerView
    private lateinit var buyAdapter: BuyTransactionAdapter
    private lateinit var buyLayoutManager: RecyclerView.LayoutManager

    private lateinit var sellRecyclerView: RecyclerView
    private lateinit var sellAdapter: SellTransactionAdapter
    private lateinit var sellLayoutManager: RecyclerView.LayoutManager

    private val retrofit = RetrofitRepository().getRetrofitInstance()

    private var buyList = ArrayList<BuyCoins>()
    private val buyCoinsRepo = retrofit.create(BuyCoinsRepository::class.java)

    private var sellList = ArrayList<SellCoins>()
    private val sellCoinsRepo = retrofit.create(SellCoinsRepository::class.java)

    private var tradesList = ArrayList<TradeCoins>()
    private val tradeCoinsRepo = retrofit.create(TradeCoinsRepository::class.java)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.transactions_fragment, container, false)
        val currentUserId = this.context?.applicationContext?.getSharedPreferences("BlockChainPreferences", Context.MODE_PRIVATE)?.getString("userId","DefUserId") as String
        Log.d("Debug","Current user for transactions $currentUserId")
        tradeRecyclerView = view.findViewById(R.id.rvTradeTransactions)
        tradeAdapter = TradeTransactionAdapter(tradesList)
        tradeLayoutManager = LinearLayoutManager(context)
        tradeRecyclerView.adapter = tradeAdapter
        tradeRecyclerView.layoutManager = tradeLayoutManager

        buyRecyclerView = view.findViewById(R.id.rvBuyTransactions)
        buyAdapter = BuyTransactionAdapter(buyList)
        buyLayoutManager = LinearLayoutManager(context)
        buyRecyclerView.adapter = buyAdapter
        buyRecyclerView.layoutManager = buyLayoutManager

        sellRecyclerView = view.findViewById(R.id.rvSellTransactions)
        sellAdapter = SellTransactionAdapter(sellList)
        sellLayoutManager = LinearLayoutManager(context)
        sellRecyclerView.adapter = sellAdapter
        sellRecyclerView.layoutManager = sellLayoutManager

        val tradeCoinsListCall = tradeCoinsRepo.getTradeCoinsTransactions()

        tradeCoinsListCall.enqueue(object: Callback<List<TradeCoins>>{
            override fun onResponse(call: Call<List<TradeCoins>>, response: Response<List<TradeCoins>>) {
                tradesList = response.body() as ArrayList<TradeCoins>
                tradeAdapter.list = tradesList
                tradeAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<TradeCoins>>, t: Throwable) {
                Log.d("Debug","Could not load transactions data",t)
                Toast.makeText(context,"Could not retrieve transaction history",Toast.LENGTH_SHORT).show()
            }
        })

        buyCoinsRepo.getBuyTransactionByClient("resource:org.tunki.network.Client#$currentUserId").enqueue(object: Callback<List<BuyCoins>>{
            override fun onResponse(call: Call<List<BuyCoins>>, response: Response<List<BuyCoins>>) {
                if(response.body()==null)
                {
                    Log.d("Debug",response.message())
                    return
                }
                else
                {
                    Log.d("Debug",response.message())
                }
                buyList = response.body() as ArrayList<BuyCoins>
                buyAdapter.list = buyList
                buyAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<BuyCoins>>, t: Throwable) {
                Log.d("Debug","Could not load transaction data",t)
                Toast.makeText(context,"Could not retrieve transaction history",Toast.LENGTH_SHORT).show()
            }
        })

        sellCoinsRepo.getSellCoinsTransactions().enqueue(object: Callback<List<SellCoins>>{
            override fun onResponse(call: Call<List<SellCoins>>, response: Response<List<SellCoins>>) {
                sellList = response.body() as ArrayList<SellCoins>
                sellAdapter.list = sellList
                sellAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<SellCoins>>, t: Throwable) {
                Log.d("Debug","Could not load transaction data",t)
                Toast.makeText(context,"Could not retrieve transaction history",Toast.LENGTH_SHORT).show()
            }
        })
        return view
    }
}
