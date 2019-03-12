package pe.edu.upc.tunkiblockchain.repository

import pe.edu.upc.tunkiblockchain.models.Client
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ClientRepository {
    @GET("Client/{id}")
    fun getClient(@Path("id") clientId: String): Call<Client>

    @GET("Client")
    fun getAllClients(): Call<List<Client>>

    @POST("Client")
    fun postClient(@Body client : Client) : Call<Client>
}