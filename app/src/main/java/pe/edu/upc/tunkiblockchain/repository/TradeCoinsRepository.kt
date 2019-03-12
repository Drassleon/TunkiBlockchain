package pe.edu.upc.tunkiblockchain.repository

import pe.edu.upc.tunkiblockchain.models.TradeCoins
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TradeCoinsRepository {
    @POST("TradeCoins")
    fun postTradeCoins(@Body tradeCoins: TradeCoins): Call<TradeCoins>
    @GET("TradeCoins")
    fun getTradeCoinsTransactions(): Call<List<TradeCoins>>
    @GET("TradeCoins/{id}")
    fun getTradeCoinsTransaction(@Path("id") transactionId: String): Call<TradeCoins>
}