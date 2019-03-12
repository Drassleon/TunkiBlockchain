package pe.edu.upc.tunkiblockchain.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import pe.edu.upc.tunkiblockchain.R
import pe.edu.upc.tunkiblockchain.adapter.ShopAdapter
import pe.edu.upc.tunkiblockchain.models.CoinProvider
import pe.edu.upc.tunkiblockchain.repository.CoinProviderRepository
import pe.edu.upc.tunkiblockchain.repository.RetrofitRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ShopFragment : Fragment() {

    private lateinit var shopRecyclerView: RecyclerView
    private lateinit var shopAdapter: ShopAdapter
    private lateinit var shopLayoutManager: RecyclerView.LayoutManager
    private val shopRepo = RetrofitRepository().getRetrofitInstance().create(CoinProviderRepository::class.java)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.shop_fragment, container, false)
        var shops = ArrayList<CoinProvider>()
        shopRecyclerView = view.findViewById(R.id.rvShops)
        shopAdapter = ShopAdapter(shops)
        shopLayoutManager = LinearLayoutManager(this@ShopFragment.context)
        shopRecyclerView.adapter = shopAdapter
        shopRecyclerView.layoutManager = shopLayoutManager
        val shopsCall = shopRepo.getAllCoinProviders()
        shopsCall.enqueue(object: Callback<List<CoinProvider>>{
            override fun onResponse(call: Call<List<CoinProvider>>, response: Response<List<CoinProvider>>) {
                shops = response.body() as ArrayList<CoinProvider>
                shopAdapter.list = shops
                shopAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<CoinProvider>>, t: Throwable) {
                Toast.makeText(this@ShopFragment.context,"Could not load Shop Data",Toast.LENGTH_SHORT).show()
                Log.d("Debug","Could not load Shop Data",t)
            }
        })
        return view
    }
}