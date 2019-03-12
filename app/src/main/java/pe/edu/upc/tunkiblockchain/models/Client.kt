package pe.edu.upc.tunkiblockchain.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Client : Serializable{
    @SerializedName("\$class")
    @Expose
    var `$class`: String? = "org.tunki.network.Client"
    @SerializedName("clientId")
    @Expose
    var clientId: String? = null
    @SerializedName("clientName")
    @Expose
    var clientName: String? = null
    @SerializedName("savings")
    @Expose
    var savings: Double? = null
}