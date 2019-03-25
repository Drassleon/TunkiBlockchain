package pe.edu.upc.tunkiblockchain.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import pe.edu.upc.tunkiblockchain.R
import pe.edu.upc.tunkiblockchain.models.Client
import pe.edu.upc.tunkiblockchain.viewholders.activities.PayContactActivity

class ContactAdapter(var list: List<Client>): RecyclerView.Adapter<ContactViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ContactViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.contacts_view_holder, p0, false)
        return ContactViewHolder(view)    }

    override fun onBindViewHolder(holder: ContactViewHolder, pos: Int) {
        holder.contactNameTextView.text = list[pos].clientName
        holder.contactPhoneNumberTextView.text = "123456789"
        when(list[pos].clientName)
        {
            "Juan Paul Rodriguez"->holder.contactProfileImageView.setImageResource(R.drawable.juan_image_profile)
            "Noelia Barragan"->holder.contactProfileImageView.setImageResource(R.drawable.noelia_image_profile)
            "Constanza Salinas"->holder.contactProfileImageView.setImageResource(R.drawable.constanza_image_profile)
            "Mauricio Sanchez"->holder.contactProfileImageView.setImageResource(R.drawable.mauricio_image_profile)
            "Alvaro Toconas"->holder.contactProfileImageView.setImageResource(R.drawable.alvaro_image_profile)
            "David Tito"->holder.contactProfileImageView.setImageResource(R.drawable.ibk_logo)
        }

        holder.rootContacts.setOnClickListener {
            val context = it.context
            context.startActivity(Intent(context,PayContactActivity::class.java).putExtra("contact",list[pos]))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}