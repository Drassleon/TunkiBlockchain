package pe.edu.upc.tunkiblockchain.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
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
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SellTransactionViewHolder(itemView: View) : TransactionViewHolder(itemView) {
    val shopNameSellTransaction = itemView.findViewById(R.id.tvShopNameSellTransaction) as TextView
    val amountSellTransactionTextView = itemView.findViewById(R.id.tvAmountSellTransactions) as TextView
    val timestampTextView = itemView.findViewById(R.id.tvTimeStampSell) as TextView
    val sharedPref = itemView.context.applicationContext.getSharedPreferences("BlockChainPreferences", Context.MODE_PRIVATE)
    val sellIcon = itemView.findViewById(R.id.SellTransactionIcon) as ImageView
    override fun bindType(item: TransactionsTypes) {
        val shopRepo = RetrofitRepository().getRetrofitInstance().create(CoinProviderRepository::class.java)
        val typedItem = item as SellCoins
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
                shopNameSellTransaction.text = shop.coinProviderName
                amountSellTransactionTextView.text = "- S/. ${typedItem.amount.toString()}"
                timestampTextView.text = "$datetimeFormatted"
                if(shop.coinProviderName=="Plaza Vea")
                {
                    sellIcon.setImageResource(R.drawable.plaza_vea_logo)
                }
                else if(shop.coinProviderName=="Agente IBK Torre"||shop.coinProviderName=="Cajero IBK Torre"){
                    sellIcon.setImageResource(R.drawable.ibk_logo)
                }
            }

            override fun onFailure(call: Call<CoinProvider>, t: Throwable) {
                Log.d("Debug","Could not retrieve shop data from shop ${unFormattedList[1]}",t)
            }
        })    }
}