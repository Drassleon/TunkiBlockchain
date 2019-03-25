package pe.edu.upc.tunkiblockchain.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.TextView
import pe.edu.upc.tunkiblockchain.R
import pe.edu.upc.tunkiblockchain.models.CoinProvider
import pe.edu.upc.tunkiblockchain.models.SellCoins
import pe.edu.upc.tunkiblockchain.repository.CoinProviderRepository
import pe.edu.upc.tunkiblockchain.repository.RetrofitRepository
import pe.edu.upc.tunkiblockchain.utils.TransactionsTypes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SellTransactionViewHolder(itemView: View) : TransactionViewHolder(itemView) {
    val shopNameSellTransaction = itemView.findViewById(R.id.tvShopNameSellTransaction) as TextView
    val amountSellTransactionTextView = itemView.findViewById(R.id.tvAmountSellTransactions) as TextView
    val timestampTextView = itemView.findViewById(R.id.tvTimeStampSell) as TextView
    val sharedPref = itemView.context.applicationContext.getSharedPreferences("BlockChainPreferences", Context.MODE_PRIVATE)

    override fun bindType(item: TransactionsTypes) {
        val shopRepo = RetrofitRepository().getRetrofitInstance().create(CoinProviderRepository::class.java)
        val typedItem = item as SellCoins
        val unFormattedShopName = typedItem.shop as String
        val unFormattedList = unFormattedShopName.split("#")

        val unFormattedTimestamp = typedItem.timestamp as String
        val unFormattedListTime = unFormattedTimestamp.split("T")
        val date = unFormattedListTime[0]
        val unFormattedTime = unFormattedListTime[1].split(".")
        val time = unFormattedTime[0]

        val shopCall = shopRepo.getCoinProvider(unFormattedList[1],sharedPref.getString("api_key","api_key") as String)
        var shop : CoinProvider
        shopCall.enqueue(object: Callback<CoinProvider> {
            override fun onResponse(call: Call<CoinProvider>, response: Response<CoinProvider>) {
                shop = response.body() as CoinProvider
                shopNameSellTransaction.text = shop.coinProviderName
                amountSellTransactionTextView.text = "S/. ${typedItem.amount.toString()}"
                timestampTextView.text = "$time at $date"
            }

            override fun onFailure(call: Call<CoinProvider>, t: Throwable) {
                Log.d("Debug","Could not retrieve shop data from shop ${unFormattedList[1]}",t)
            }
        })    }
}