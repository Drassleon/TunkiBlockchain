package pe.edu.upc.tunkiblockchain.fragments

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
import pe.edu.upc.tunkiblockchain.adapter.TradeTransactionAdapter
import pe.edu.upc.tunkiblockchain.models.TradeCoins
import pe.edu.upc.tunkiblockchain.repository.RetrofitRepository
import pe.edu.upc.tunkiblockchain.repository.TradeCoinsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionsFragment : Fragment() {

    private lateinit var tradeRecyclerView: RecyclerView
    private lateinit var tradeAdapter: TradeTransactionAdapter
    private lateinit var tradeLayoutManager: RecyclerView.LayoutManager
    private var tradesList = ArrayList<TradeCoins>()
    private val retrofit = RetrofitRepository().getRetrofitInstance()
    private val tradeCoinsRepo = retrofit.create(TradeCoinsRepository::class.java)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.transactions_fragment, container, false)
        tradeRecyclerView = view.findViewById(R.id.rvTransactions)
        tradeAdapter = TradeTransactionAdapter(tradesList)
        tradeLayoutManager = LinearLayoutManager(context)
        tradeRecyclerView.adapter = tradeAdapter
        tradeRecyclerView.layoutManager = tradeLayoutManager
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
        return view
    }
}
