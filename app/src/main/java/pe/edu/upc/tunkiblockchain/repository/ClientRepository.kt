package pe.edu.upc.tunkiblockchain.repository

import pe.edu.upc.tunkiblockchain.models.Client
import retrofit2.Call
import retrofit2.http.*

interface ClientRepository {
    @GET("Client/{id}")
    fun getClient(@Path("id") clientId: String,@Query("access_token")tokenKey: String): Call<Client>

    @GET("Client")
    fun getAllClients(@Query("access_token")tokenKey: String): Call<List<Client>>

    @POST("Client")
    fun postClient(@Body client : Client,@Query("access_token")tokenKey: String) : Call<Client>
}