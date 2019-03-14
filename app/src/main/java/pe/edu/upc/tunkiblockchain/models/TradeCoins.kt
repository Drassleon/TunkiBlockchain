package pe.edu.upc.tunkiblockchain.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import pe.edu.upc.tunkiblockchain.utils.TransactionsTypes
import java.io.Serializable


class TradeCoins: Serializable,TransactionsTypes{
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
    @Expose(serialize = false,deserialize = false)
    override val txType: Int = 1
    override fun getTransactionType(): Int {
        return txType
    }
}