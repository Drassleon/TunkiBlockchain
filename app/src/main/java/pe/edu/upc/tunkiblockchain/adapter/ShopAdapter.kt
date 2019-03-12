package pe.edu.upc.tunkiblockchain.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import pe.edu.upc.tunkiblockchain.R
import pe.edu.upc.tunkiblockchain.models.CoinProvider
import pe.edu.upc.tunkiblockchain.viewholders.activities.PayBuyShopActivity

class ShopAdapter(var list: List<CoinProvider>): RecyclerView.Adapter<ShopViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ShopViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.shop_view_holder, p0, false)
        return ShopViewHolder(view)    }

    override fun onBindViewHolder(holder: ShopViewHolder, pos: Int) {
        holder.shopNameTextView.text = list[pos].coinProviderName
        holder.rootShops.setOnClickListener {
            val context = it.context
            context.startActivity(Intent(context, PayBuyShopActivity::class.java).putExtra("shop",list[pos]))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}