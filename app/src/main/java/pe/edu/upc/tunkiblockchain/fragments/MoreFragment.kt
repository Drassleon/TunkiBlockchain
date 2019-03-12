package pe.edu.upc.tunkiblockchain.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import pe.edu.upc.tunkiblockchain.R
import pe.edu.upc.tunkiblockchain.viewholders.activities.LoginActivity

class MoreFragment : Fragment() {

    private lateinit var logoutButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.more_fragment, container, false)
        val sharedPref = view.context.applicationContext.getSharedPreferences("BlockChainPreferences", Context.MODE_PRIVATE)
        logoutButton = view.findViewById(R.id.btnLogout)
        logoutButton.setOnClickListener {
            sharedPref.edit().putBoolean("logged",false).apply()
            Log.d("Debug",sharedPref.getBoolean("logged",true).toString())
            view.context.startActivity(Intent(view.context, LoginActivity::class.java))
        }
        return view
    }
}