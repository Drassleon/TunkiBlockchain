package pe.edu.upc.tunkiblockchain.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import pe.edu.upc.tunkiblockchain.R
import pe.edu.upc.tunkiblockchain.models.BuyCoins
import pe.edu.upc.tunkiblockchain.models.CoinProvider
import pe.edu.upc.tunkiblockchain.repository.CoinProviderRepository
import pe.edu.upc.tunkiblockchain.repository.RetrofitRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuyTransactionAdapter(var list: List<BuyCoins>) : RecyclerView.Adapter<BuyTransactionViewHolder>() {
    override fun onBindViewHolder(holder: BuyTransactionViewHolder, position: Int) {
        val shopRepo = RetrofitRepository().getRetrofitInstance().create(CoinProviderRepository::class.java)

        val unFormattedShopName = list[position].shop as String
        val unFormattedList = unFormattedShopName.split("#")

        val unFormattedTimestamp = list[position].timestamp as String
        val unFormattedListTime = unFormattedTimestamp.split("T")
        val date = unFormattedListTime[0]
        val unFormattedTime = unFormattedListTime[1].split(".")
        val time = unFormattedTime[0]

        val shopCall = shopRepo.getCoinProvider(unFormattedList[1],holder.sharedPref.getString("api_key","api_key") as String)
        var shop : CoinProvider
        shopCall.enqueue(object: Callback<CoinProvider> {
            override fun onResponse(call: Call<CoinProvider>, response: Response<CoinProvider>) {
                shop = response.body() as CoinProvider
                holder.shopNameBuyTransaction.text = shop.coinProviderName
                holder.amountBuyTransactionTextView.text = "S/. ${list[position].amount.toString()}"
                holder.timestampTextView.text = "$time at $date"
            }

            override fun onFailure(call: Call<CoinProvider>, t: Throwable) {
                Log.d("Debug","Could not retrieve shop data from shop ${unFormattedList[1]}",t)
            }
        })

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyTransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.buy_transaction_view_holder, parent, false)
        return BuyTransactionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}