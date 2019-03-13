package pe.edu.upc.tunkiblockchain.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import pe.edu.upc.tunkiblockchain.R

class BuyTransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val shopNameBuyTransaction = itemView.findViewById<TextView>(R.id.tvShopNameBuyTransaction)
    val amountButTrasactionTextView = itemView.findViewById<TextView>(R.id.tvAmountBuyTransactions)
    val timestampTextView = itemView.findViewById<TextView>(R.id.tvTimeStampBuy)
}