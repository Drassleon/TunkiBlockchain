package pe.edu.upc.tunkiblockchain.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import pe.edu.upc.tunkiblockchain.R

class ClientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val contactNameTextView = itemView.findViewById(R.id.tvContactNameTransactions) as TextView
    val amountTransactedTextView = itemView.findViewById(R.id.tvAmountTransactions) as TextView
    val timestampTextView = itemView.findViewById(R.id.tvTimeStamp) as TextView

}