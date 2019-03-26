package pe.edu.upc.tunkiblockchain.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import pe.edu.upc.tunkiblockchain.R
import pe.edu.upc.tunkiblockchain.adapter.ContactAdapter
import pe.edu.upc.tunkiblockchain.models.Client
import pe.edu.upc.tunkiblockchain.repository.ClientRepository
import pe.edu.upc.tunkiblockchain.repository.RetrofitRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ContactFragment : Fragment() {
    private var contactsList = ArrayList<Client>()
    private lateinit var contactRecyclerView: RecyclerView
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var contactLayoutManager: RecyclerView.LayoutManager
    private lateinit var tokenAmountTextView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.contacts_fragment, container, false)

        val retrofit = RetrofitRepository().getRetrofitInstance()
        val clientRepo = retrofit.create(ClientRepository::class.java)
        val sharedPref = context?.applicationContext?.getSharedPreferences("BlockChainPreferences",Context.MODE_PRIVATE)
        val currentUser = sharedPref?.getString("userId","DefUserId")
        Log.d("Debug",currentUser)
        tokenAmountTextView = view.findViewById(R.id.tvAmountTokensContacts)
        contactRecyclerView = view.findViewById(R.id.rvContacts)
        contactAdapter = ContactAdapter(contactsList)
        contactLayoutManager = LinearLayoutManager(this.context)
        contactRecyclerView.adapter = contactAdapter
        contactRecyclerView.layoutManager = contactLayoutManager
        contactAdapter.notifyDataSetChanged()

        val contactListCall = clientRepo.getAllClients(sharedPref?.getString("api_key","api_key")as String)
        Log.d("Debug",contactListCall.request().toString())
        contactListCall.enqueue(object: Callback<List<Client>> {
            override fun onResponse(call: Call<List<Client>>, response: Response<List<Client>>) {
                if(response.body()==null)
                {
                    Log.d("Debug",response.body().toString())
                    return
                }
                contactsList = response.body() as ArrayList<Client>
                var currentClient = Client()
                for(contact in contactsList)
                {
                    if(contact.clientId==currentUser)
                    {
                        currentClient=contact
                        tokenAmountTextView.text = currentClient.savings.toString()
                        sharedPref.edit().putString("amountTokens",currentClient.savings.toString()).apply()
                    }
                }
                contactsList.remove(currentClient)
                contactAdapter.list = contactsList
                contactAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Client>>, t: Throwable) {
                Log.d("RestService","Could not retrieve client data",t)
                Toast.makeText(context,"Could not retrieve contact data :(",Toast.LENGTH_SHORT).show()
            }
        }
        )
        return view
    }
}