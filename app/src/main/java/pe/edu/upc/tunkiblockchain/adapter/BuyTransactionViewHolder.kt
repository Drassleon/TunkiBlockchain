package pe.edu.upc.tunkiblockchain.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import pe.edu.upc.tunkiblockchain.R
import pe.edu.upc.tunkiblockchain.models.BuyCoins
import pe.edu.upc.tunkiblockchain.models.CoinProvider
import pe.edu.upc.tunkiblockchain.repository.CoinProviderRepository
import pe.edu.upc.tunkiblockchain.repository.RetrofitRepository
import pe.edu.upc.tunkiblockchain.utils.TransactionsTypes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BuyTransactionViewHolder(itemView: View) : TransactionViewHolder(itemView) {
    val shopNameBuyTransaction = itemView.findViewById(R.id.tvShopNameBuyTransaction) as TextView
    val amountBuyTransactionTextView = itemView.findViewById(R.id.tvAmountBuyTransactions) as TextView
    val timestampTextView = itemView.findViewById(R.id.tvTimeStampBuy) as TextView
    val buyIcon = itemView.findViewById(R.id.BuyTransactionIcon) as ImageView
    val sharedPref = itemView.context.applicationContext.getSharedPreferences("BlockChainPreferences", Context.MODE_PRIVATE)

    override fun bindType(item: TransactionsTypes) {
        val shopRepo = RetrofitRepository().getRetrofitInstance().create(CoinProviderRepository::class.java)
        val typedItem = item as BuyCoins
        val unFormattedShopName = typedItem.shop as String
        val unFormattedList = unFormattedShopName.split("#")

        val unFormattedTimestamp = typedItem.timestamp as String
        val unFormattedListTime = unFormattedTimestamp.split("T")
        val date = unFormattedListTime[0]
        val unFormattedTime = unFormattedListTime[1].split(".")
        val days = date.split("-")[2].toInt()
        Log.d("Debug",days.toString())
        val months = date.split("-")[1].toInt()
        Log.d("Debug",months.toString())
        val years = date.split("-")[0].toInt()
        Log.d("Debug",years.toString())

        val hours = unFormattedTime[0].split(":")[0].toInt()
        Log.d("Debug",hours.toString())
        val minutes = unFormattedTime[0].split(":")[1].toInt()
        Log.d("Debug",minutes.toString())
        val seconds = unFormattedTime[0].split(":")[2].toInt()
        Log.d("Debug",seconds.toString())


        var formatter1 = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy")
        var datetimeFormatted = formatter1.format(LocalDateTime.of(years,months,days,hours,minutes,seconds).minusHours(5))

        val shopCall = shopRepo.getCoinProvider(unFormattedList[1],sharedPref.getString("api_key","api_key") as String)
        var shop : CoinProvider
        shopCall.enqueue(object: Callback<CoinProvider> {
            override fun onResponse(call: Call<CoinProvider>, response: Response<CoinProvider>) {
                shop = response.body() as CoinProvider
                shopNameBuyTransaction.text = shop.coinProviderName
                amountBuyTransactionTextView.text = "S/. ${(item as BuyCoins).amount.toString()}"
                timestampTextView.text = "$datetimeFormatted"
                if(shop.coinProviderName=="Plaza Vea")
                {
                    buyIcon.setImageResource(R.drawable.plaza_vea_logo)
                }
                else if(shop.coinProviderName=="Agente IBK Torre"||shop.coinProviderName=="Cajero IBK Torre"){
                    buyIcon.setImageResource(R.drawable.ibk_logo)
                }
            }

            override fun onFailure(call: Call<CoinProvider>, t: Throwable) {
                Log.d("Debug","Could not retrieve shop data from shop ${unFormattedList[1]}",t)
            }
        })
    }
}