package pe.edu.upc.tunkiblockchain.repository

import pe.edu.upc.tunkiblockchain.models.TradeCoins
import retrofit2.Call
import retrofit2.http.*

interface TradeCoinsRepository {
    @POST("TradeCoins")
    fun postTradeCoins(@Body tradeCoins: TradeCoins): Call<TradeCoins>
    @GET("TradeCoins")
    fun getTradeCoinsTransactions(): Call<List<TradeCoins>>
    @GET("TradeCoins/{id}")
    fun getTradeCoinsTransaction(@Path("id") transactionId: String): Call<TradeCoins>
    @GET("queries/SelectTradesbyClientFrom")
    fun getTradebyClientFrom(@Query("clientId")clientId: String): Call<List<TradeCoins>>
}