package pe.edu.upc.tunkiblockchain.repository

import pe.edu.upc.tunkiblockchain.models.CoinProvider
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CoinProviderRepository {
    @GET("CoinProvider/{id}")
    fun getCoinProvider(@Path("id") coinProviderId : String) : Call<CoinProvider>
    @GET("CoinProvider")
    fun getAllCoinProviders() : Call<List<CoinProvider>>
    @POST("CoinProvider")
    fun postCoinProvider(@Body coinProvider: CoinProvider): Call<CoinProvider>
}