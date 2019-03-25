package pe.edu.upc.tunkiblockchain.repository

import pe.edu.upc.tunkiblockchain.models.BuyCoins
import retrofit2.Call
import retrofit2.http.*

interface BuyCoinsRepository {
    @POST("BuyCoins")
    fun postBuyCoinTransaction(@Body buyCoins: BuyCoins,@Query("access_token")tokenKey: String): Call<BuyCoins>
    @GET("BuyCoins")
    fun getBuyCoinsTransactions(@Query("access_token")tokenKey: String): Call<List<BuyCoins>>
    @GET("BuyCoins/{id}")
    fun getBuyCoinsTransaction(@Path("id") transactionId: String,@Query("access_token")tokenKey: String): Call<BuyCoins>
    @GET("queries/SelectCoinPurchasebyClient")
    fun getBuyTransactionByClient(@Query("clientId") clientId: String): Call<List<BuyCoins>>

}