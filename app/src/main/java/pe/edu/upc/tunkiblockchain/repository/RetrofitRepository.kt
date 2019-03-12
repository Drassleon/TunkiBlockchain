package pe.edu.upc.tunkiblockchain.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRepository{

    companion object {
        private var BASE_URL : String = "http://35.226.25.204:3000/api/"
        private lateinit var retrofit : Retrofit
    }

    fun getRetrofitInstance() : Retrofit {

        retrofit = retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}