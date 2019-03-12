package pe.edu.upc.tunkiblockchain.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import pe.edu.upc.tunkiblockchain.R

class ShopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val shopNameTextView =  itemView.findViewById(R.id.tvShopName) as TextView
    val shopProfileImageView = itemView.findViewById(R.id.ShopImageProfile) as ImageView
    val sharedPref = itemView.context.applicationContext.getSharedPreferences("BlockChainPreferences", Context.MODE_PRIVATE)
    val rootShops = itemView.findViewById(R.id.rootShops) as ConstraintLayout
}