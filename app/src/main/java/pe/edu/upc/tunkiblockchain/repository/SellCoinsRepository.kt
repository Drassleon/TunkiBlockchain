package pe.edu.upc.tunkiblockchain.repository

import pe.edu.upc.tunkiblockchain.models.SellCoins
import retrofit2.Call
import retrofit2.http.*

interface SellCoinsRepository {
    @POST("SellCoins")
    fun postSellCoinsTransaction(@Body sellCoins: SellCoins): Call<SellCoins>
    @GET("SellCoins")
    fun getSellCoinsTransactions(): Call<List<SellCoins>>
    @GET("SellCoins/{id}")
    fun getSellCoinsTransaction(@Path("id") transactionId: String): Call<SellCoins>
    @GET("queries/SelectCoinSalebyClient")
    fun getSellTransactionByClient(@Query("clientId") clientId: String): Call<List<SellCoins>>
}