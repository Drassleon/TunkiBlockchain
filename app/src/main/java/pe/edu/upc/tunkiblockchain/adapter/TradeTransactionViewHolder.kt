package pe.edu.upc.tunkiblockchain.adapter

import android.content.Context
import android.util.Log
import android.view.View
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

class TradeTransactionViewHolder(itemView: View) : TransactionViewHolder(itemView) {

    val contactNameTextView = itemView.findViewById(R.id.tvContactNameTransactions) as TextView
    val amountTransactedTextView = itemView.findViewById(R.id.tvAmountTransactions) as TextView
    val timestampTextView = itemView.findViewById(R.id.tvTimeStamp) as TextView
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
        val time = unFormattedTime[0]

        val contactCall = clientRepo.getClient(unFormattedList[1],sharedPref.getString("api_key","api_key") as String)
        var contact : Client
        contactCall.enqueue(object: Callback<Client> {
            override fun onResponse(call: Call<Client>, response: Response<Client>) {
                contact = response.body() as Client
                contactNameTextView.text = contact.clientName
                amountTransactedTextView.text = "S/. ${typedItem.amount.toString()}"
                timestampTextView.text = "$time at $date"
            }

            override fun onFailure(call: Call<Client>, t: Throwable) {
                Log.d("Debug","Could not retrieve client data from client ${unFormattedList[1]}",t)
            }
        })
    }
}