package pe.edu.upc.tunkiblockchain.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import pe.edu.upc.tunkiblockchain.R
import pe.edu.upc.tunkiblockchain.models.Client
import pe.edu.upc.tunkiblockchain.models.TradeCoins
import pe.edu.upc.tunkiblockchain.repository.ClientRepository
import pe.edu.upc.tunkiblockchain.repository.RetrofitRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TradeTransactionAdapter(var list: List<TradeCoins>) : RecyclerView.Adapter<TradeTransactionViewHolder>() {
    override fun onBindViewHolder(holder: TradeTransactionViewHolder, position: Int) {
        val clientRepo = RetrofitRepository().getRetrofitInstance().create(ClientRepository::class.java)

        val unFormattedContactName = list[position].clientTo as String
        val unFormattedList = unFormattedContactName.split("#")

        val unFormattedTimestamp = list[position].timestamp as String
        val unFormattedListTime = unFormattedTimestamp.split("T")
        val date = unFormattedListTime[0]
        val unFormattedTime = unFormattedListTime[1].split(".")
        val time = unFormattedTime[0]

        val contactCall = clientRepo.getClient(unFormattedList[1],holder.sharedPref.getString("api_key","api_key")as String)
        var contact : Client = Client()
        contactCall.enqueue(object: Callback<Client>{
            override fun onResponse(call: Call<Client>, response: Response<Client>) {
                contact = response.body() as Client
                holder.contactNameTextView.text = contact.clientName
                holder.amountTransactedTextView.text = "S/. ${list[position].amount.toString()}"
                holder.timestampTextView.text = "$time at $date"
            }

            override fun onFailure(call: Call<Client>, t: Throwable) {
                Log.d("Debug","Could not retrieve client data from client ${unFormattedList[1]}",t)
            }
        })

     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TradeTransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.trade_transaction_view_holder, parent, false)
        return TradeTransactionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}