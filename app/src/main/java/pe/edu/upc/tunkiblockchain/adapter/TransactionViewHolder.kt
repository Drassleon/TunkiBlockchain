package pe.edu.upc.tunkiblockchain.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import pe.edu.upc.tunkiblockchain.utils.TransactionsTypes


abstract class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bindType(item: TransactionsTypes)
}