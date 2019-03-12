package pe.edu.upc.tunkiblockchain.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import pe.edu.upc.tunkiblockchain.R

class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val contactNameTextView =  itemView.findViewById(R.id.tvContactName) as TextView
    val contactPhoneNumberTextView = itemView.findViewById(R.id.tvPhoneNumber) as TextView
    val contactProfileImageView = itemView.findViewById(R.id.ContactImageProfile) as ImageView
    val sharedPref = itemView.context.applicationContext.getSharedPreferences("BlockChainPreferences", Context.MODE_PRIVATE)
    val rootContacts = itemView.findViewById(R.id.rootContacts) as ConstraintLayout
}