package dk.fantastiskefroe.it.shopify_data.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import dk.fantastiskefroe.it.shopify_data.entity.Order
import dk.fantastiskefroe.it.shopify_data.entity.OrderLine


@JsonIgnoreProperties(ignoreUnknown = true)
data class CreateOrderLineDTO(
    val sku: String,
    val title: String,
    @JsonProperty("variant_title")
    val variantTitle: String?,
    val quantity: Int,
    val price: Double?
)

fun CreateOrderLineDTO.toOrderLine(parent: Order) : OrderLine {
    return OrderLine().also {
        it.sku = sku
        it.title = title
        it.variant_title = variantTitle
        it.quantity = quantity
        it.price = price
        it.order = parent
    }
}
