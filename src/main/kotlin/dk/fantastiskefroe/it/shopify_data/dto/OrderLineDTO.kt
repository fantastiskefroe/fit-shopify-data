package dk.fantastiskefroe.it.shopify_data.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import dk.fantastiskefroe.it.shopify_data.entity.*

@JsonIgnoreProperties(ignoreUnknown = true)
data class OrderLineDTO(
    val sku : String,
    val title : String,
    val variantTitle : String?,
    val quantity: Int,
    val price : Double?
) { companion object }

fun OrderLineDTO.Companion.from(source: OrderLine) : OrderLineDTO {
    return OrderLineDTO(
        sku = source.sku,
        title = source.title,
        variantTitle = source.variant_title,
        quantity = source.quantity,
        price = source.price
    )
}
