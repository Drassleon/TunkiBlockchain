package pe.edu.upc.tunkiblockchain.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import pe.edu.upc.tunkiblockchain.R
import pe.edu.upc.tunkiblockchain.models.CoinProvider
import pe.edu.upc.tunkiblockchain.models.SellCoins
import pe.edu.upc.tunkiblockchain.repository.CoinProviderRepository
import pe.edu.upc.tunkiblockchain.repository.RetrofitRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SellTransactionAdapter(var list: List<SellCoins>) : RecyclerView.Adapter<SellTransactionViewHolder>() {
    override fun onBindViewHolder(holder: SellTransactionViewHolder, position: Int) {
        val shopRepo = RetrofitRepository().getRetrofitInstance().create(CoinProviderRepository::class.java)

        val unFormattedShopName = list[position].shop as String
        val unFormattedList = unFormattedShopName.split("#")

        val unFormattedTimestamp = list[position].timestamp as String
        val unFormattedListTime = unFormattedTimestamp.split("T")
        val date = unFormattedListTime[0]
        val unFormattedTime = unFormattedListTime[1].split(".")
        val time = unFormattedTime[0]

        val shopCall = shopRepo.getCoinProvider(unFormattedList[1])
        var shop : CoinProvider
        shopCall.enqueue(object: Callback<CoinProvider> {
            override fun onResponse(call: Call<CoinProvider>, response: Response<CoinProvider>) {
                shop = response.body() as CoinProvider
                holder.shopNameSellTransaction.text = shop.coinProviderName
                holder.amountSellTrasactionTextView.text = "S/. ${list[position].amount.toString()}"
                holder.timestampTextView.text = "$time at $date"
            }

            override fun onFailure(call: Call<CoinProvider>, t: Throwable) {
                Log.d("Debug","Could not retrieve shop data from shop ${unFormattedList[1]}",t)
            }
        })

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellTransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sell_transaction_view_holder, parent, false)
        return SellTransactionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}