package pe.edu.upc.tunkiblockchain.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import pe.edu.upc.tunkiblockchain.R
import pe.edu.upc.tunkiblockchain.models.Client
import pe.edu.upc.tunkiblockchain.models.TradeCoins
import pe.edu.upc.tunkiblockchain.repository.ClientRepository
import pe.edu.upc.tunkiblockchain.repository.RetrofitRepository
import pe.edu.upc.tunkiblockchain.utils.TransactionsTypes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TradeTransactionViewHolder(itemView: View) : TransactionViewHolder(itemView) {

    val contactNameTextView = itemView.findViewById(R.id.tvContactNameTransactions) as TextView
    val amountTransactedTextView = itemView.findViewById(R.id.tvAmountTransactions) as TextView
    val timestampTextView = itemView.findViewById(R.id.tvTimeStamp) as TextView
    val tradeIcon = itemView.findViewById(R.id.TransactionIcon) as ImageView
    val sharedPref = itemView.context.applicationContext.getSharedPreferences("BlockChainPreferences", Context.MODE_PRIVATE)

    override fun bindType(item: TransactionsTypes) {
        val clientRepo = RetrofitRepository().getRetrofitInstance().create(ClientRepository::class.java)
        val typedItem = item as TradeCoins
        val unFormattedContactName = typedItem.clientTo as String
        val unFormattedList = unFormattedContactName.split("#")

        val unFormattedTimestamp = typedItem.timestamp as String
        val unFormattedListTime = unFormattedTimestamp.split("T")
        val date = unFormattedListTime[0]
        val unFormattedTime = unFormattedListTime[1].split(".")

        val days = date.split("-")[2].toInt()
        val months = date.split("-")[1].toInt()
        val years = date.split("-")[0].toInt()

        val hours = unFormattedTime[0].split(":")[0].toInt()
        val minutes = unFormattedTime[0].split(":")[1].toInt()
        val seconds = unFormattedTime[0].split(":")[2].toInt()


        var formatter1 = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy")
        var datetimeFormatted = formatter1.format(LocalDateTime.of(years,months,days,hours,minutes,seconds).minusHours(5))

        val contactCall = clientRepo.getClient(unFormattedList[1],sharedPref.getString("api_key","api_key") as String)
        var contact : Client
        contactCall.enqueue(object: Callback<Client> {
            override fun onResponse(call: Call<Client>, response: Response<Client>) {
                contact = response.body() as Client
                contactNameTextView.text = contact.clientName
                amountTransactedTextView.text = "S/. ${typedItem.amount.toString()}"
                timestampTextView.text = "$datetimeFormatted"
                when(contact.clientName)
                {
                    "Juan Paul Rodriguez"->tradeIcon.setImageResource(R.drawable.juan_image_profile)
                    "Noelia Barragan"->tradeIcon.setImageResource(R.drawable.noelia_image_profile)
                    "Constanza Salinas"->tradeIcon.setImageResource(R.drawable.constanza_image_profile)
                    "Mauricio Sanchez"->tradeIcon.setImageResource(R.drawable.mauricio_image_profile)
                    "Alvaro Toconas"->tradeIcon.setImageResource(R.drawable.alvaro_image_profile)
                    "David Tito"->tradeIcon.setImageResource(R.drawable.ibk_logo)
                }
            }

            override fun onFailure(call: Call<Client>, t: Throwable) {
                Log.d("Debug","Could not retrieve client data from client ${unFormattedList[1]}",t)
            }
        })
    }
}