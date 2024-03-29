package pe.edu.upc.tunkiblockchain.repository

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitIdentityIssuer {
    companion object {
        private var BASE_URL : String = "http://35.193.67.153:3001/api/"
        private lateinit var retrofit : Retrofit
    }

    fun getRetrofitInstance() : Retrofit {

        val builder = GsonBuilder()
        builder.excludeFieldsWithoutExposeAnnotation()
        val gson = builder.create()

        retrofit = retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit
    }
}