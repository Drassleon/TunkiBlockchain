package pe.edu.upc.tunkiblockchain.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import pe.edu.upc.tunkiblockchain.utils.TransactionsTypes
import java.io.Serializable


class BuyCoins: Serializable, TransactionsTypes {
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
    @Expose(serialize = false,deserialize = false)
    override val txType: Int = 2
    override fun getTransactionType(): Int {
        return txType
    }
}
