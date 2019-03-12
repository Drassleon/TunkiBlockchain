package pe.edu.upc.tunkiblockchain.repository

import pe.edu.upc.tunkiblockchain.models.BuyCoins
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BuyCoinsRepository {
    @POST("BuyCoins")
    fun postBuyCoinTransaction(@Body buyCoins: BuyCoins): Call<BuyCoins>
    @GET("BuyCoins")
    fun getBuyCoinsTransactions(): Call<List<BuyCoins>>
    @GET("BuyCoins/{id}")
    fun getBuyCoinsTransaction(@Path("id") transactionId: String): Call<BuyCoins>
}