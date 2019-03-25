package pe.edu.upc.tunkiblockchain.repository

import pe.edu.upc.tunkiblockchain.models.CoinProvider
import retrofit2.Call
import retrofit2.http.*

interface CoinProviderRepository {
    @GET("CoinProvider/{id}")
    fun getCoinProvider(@Path("id") coinProviderId : String,@Query("access_token")tokenKey: String) : Call<CoinProvider>
    @GET("CoinProvider")
    fun getAllCoinProviders(@Query("access_token")tokenKey: String) : Call<List<CoinProvider>>
    @POST("CoinProvider")
    fun postCoinProvider(@Body coinProvider: CoinProvider,@Query("access_token")tokenKey: String): Call<CoinProvider>
}