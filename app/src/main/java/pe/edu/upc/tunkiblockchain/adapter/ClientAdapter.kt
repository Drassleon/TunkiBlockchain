package pe.edu.upc.ticketrestkotlin.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import pe.edu.upc.tunkiblockchain.R
import pe.edu.upc.tunkiblockchain.adapter.ClientViewHolder
import pe.edu.upc.tunkiblockchain.models.TradeCoins


class ClientAdapter(var list: List<TradeCoins>) : RecyclerView.Adapter<ClientViewHolder>() {
    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        holder.contactNameTextView.text = list[position].clientTo
        holder.amountTransactedTextView.text = list[position].amount.toString()
        holder.timestampTextView.text = list[position].timestamp
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.client_view_holder, parent, false)
        return ClientViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}