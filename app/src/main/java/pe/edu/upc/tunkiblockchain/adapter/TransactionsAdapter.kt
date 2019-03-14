package pe.edu.upc.tunkiblockchain.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import pe.edu.upc.tunkiblockchain.R
import pe.edu.upc.tunkiblockchain.utils.TransactionsTypes


class TransactionsAdapter(var list: List<TransactionsTypes>) : RecyclerView.Adapter<TransactionViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return list[position].txType
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TransactionViewHolder {
        Log.d("Debug","P1 = $p1")
        var view = LayoutInflater.from(p0.context).inflate(R.layout.trade_transaction_view_holder,p0,false)
        if(p1==1)
        {
            view = LayoutInflater.from(p0.context).inflate(R.layout.trade_transaction_view_holder,p0,false)
            return TradeTransactionViewHolder(view)
        }
        if(p1==2)
        {
            view = LayoutInflater.from(p0.context).inflate(R.layout.buy_transaction_view_holder,p0,false)
            return BuyTransactionViewHolder(view)
        }
        if(p1==3)
        {
            view = LayoutInflater.from(p0.context).inflate(R.layout.sell_transaction_view_holder,p0,false)
            return SellTransactionViewHolder(view)
        }
        return TradeTransactionViewHolder(view)
    }

    override fun onBindViewHolder(p0: TransactionViewHolder, p1: Int) {
        val item = list[p1]
        p0.bindType(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}