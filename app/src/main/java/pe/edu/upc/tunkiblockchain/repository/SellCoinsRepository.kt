package pe.edu.upc.tunkiblockchain.repository

import pe.edu.upc.tunkiblockchain.models.SellCoins
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SellCoinsRepository {
    @POST("SellCoins")
    fun postSellCoinsTransaction(@Body sellCoins: SellCoins): Call<SellCoins>
    @GET("SellCoins")
    fun getSellCoinsTransactions(): Call<List<SellCoins>>
    @GET("SellCoins/{id}")
    fun getSellCoinsTransaction(@Path("id") transactionId: String): Call<SellCoins>
}