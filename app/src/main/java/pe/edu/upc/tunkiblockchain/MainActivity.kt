package pe.edu.upc.tunkiblockchain

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import pe.edu.upc.tunkiblockchain.fragments.ContactFragment
import pe.edu.upc.tunkiblockchain.fragments.MoreFragment
import pe.edu.upc.tunkiblockchain.fragments.ShopFragment
import pe.edu.upc.tunkiblockchain.fragments.TransactionsFragment
import pe.edu.upc.tunkiblockchain.viewholders.activities.LoginActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPref = applicationContext.getSharedPreferences("BlockChainPreferences", Context.MODE_PRIVATE)

        if(sharedPref.getBoolean("logged",false))
        this.startActivity(Intent(this, LoginActivity::class.java))

        bnvTunki.setOnNavigationItemSelectedListener(bnvFragmentsItemSelectedListener)
        bnvTunki.selectedItemId = R.id.navigation_contacts
    }

    private val bnvFragmentsItemSelectedListener : BottomNavigationView.OnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
            item ->
        return@OnNavigationItemSelectedListener navigateTo(item)
    }

    private fun navigateTo(item: MenuItem): Boolean {
        item.isChecked = true
        return supportFragmentManager
            .beginTransaction()
            .replace(R.id.flBlank, fragmentFor(item))
            .commit() > 0
    }
    private fun fragmentFor(item: MenuItem): Fragment {
        when(item.itemId) {
            R.id.navigation_contacts -> {
                return ContactFragment()
            }
            R.id.navigation_shops -> {
                return ShopFragment()
            }
            R.id.navigation_profile -> {
                return TransactionsFragment()
            }
            R.id.navigation_more -> {
                return MoreFragment()
            }
        }
        return ContactFragment()
    }
}
