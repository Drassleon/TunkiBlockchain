package pe.edu.upc.tunkiblockchain.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class BuyCoins{
    @SerializedName("\$class")
    @Expose
    val `$class`: String? = "org.tunki.network.BuyCoins"
    @SerializedName("client")
    @Expose
    var client: String? = null
    @SerializedName("shop")
    @Expose
    var shop: String? = null
    @SerializedName("amount")
    @Expose
    var amount: Double? = null
    @SerializedName("exchangeRate")
    @Expose
    var exchangeRate: Double? = null
    @SerializedName("transactionId")
    @Expose
    var transactionId: String? = null
    @SerializedName("timestamp")
    @Expose
    var timestamp: String? = null
}
