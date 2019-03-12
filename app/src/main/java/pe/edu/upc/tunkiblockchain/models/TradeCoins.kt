package pe.edu.upc.tunkiblockchain.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class TradeCoins{
    @SerializedName("\$class")
    @Expose
    val `$class`: String? = "org.tunki.network.TradeCoins"
    @SerializedName("clientFrom")
    @Expose
    var clientFrom: String? = null
    @SerializedName("clientTo")
    @Expose
    var clientTo: String? = null
    @SerializedName("amount")
    @Expose
    var amount: Double? = null
    @SerializedName("transactionId")
    @Expose
    var transactionId: String? = null
    @SerializedName("timestamp")
    @Expose
    var timestamp: String? = null
}