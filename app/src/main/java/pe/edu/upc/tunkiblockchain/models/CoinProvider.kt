package pe.edu.upc.tunkiblockchain.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CoinProvider : Serializable{
    @SerializedName("\$class")
    @Expose
    val `$class`: String? = "org.tunki.network.CoinProvider"
    @SerializedName("coinProviderId")
    @Expose
    var coinProviderId: String? = null
    @SerializedName("coinProviderName")
    @Expose
    var coinProviderName: String? = null
}