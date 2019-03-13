package pe.edu.upc.tunkiblockchain.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import pe.edu.upc.tunkiblockchain.R

class SellTransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val shopNameSellTransaction = itemView.findViewById<TextView>(R.id.tvShopNameSellTransaction)
    val amountSellTrasactionTextView = itemView.findViewById<TextView>(R.id.tvAmountSellTransactions)
    val timestampTextView = itemView.findViewById<TextView>(R.id.tvTimeStampSell)
}