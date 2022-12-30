package dk.fantastiskefroe.it.shopify_data.dto.webhook

import com.fasterxml.jackson.annotation.JsonProperty
import dk.fantastiskefroe.it.shopify_data.entity.OrderLine


data class OrderLineDTO(
    val sku: String,
    val title: String,
    val variantTitle: String?,
    val quantity: Int,
    val price: Double
)

fun OrderLineDTO.toInternal(): OrderLine {
    return OrderLine().also {
        it.sku = sku
        it.title = title
        it.variantTitle = variantTitle
        it.quantity = quantity
        it.price = price
    }
}
