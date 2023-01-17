package dk.fantastiskefroe.it.shopify_data.dto

import dk.fantastiskefroe.it.shopify_data.entity.OrderLine
import dk.fantastiskefroe.it.shopify_data.entity.RestockType

data class OrderLineDTO(
    val shopifyId: Long?,
    val sku: String,
    val title: String,
    val productId: Long?,
    val variantId: Long?,
    val variantTitle: String?,
    val refunded: Boolean,
    val restockType: RestockType,
    val quantity: Int,
    val price: Double,
    var totalDiscount: Double,
    var totalTax: Double,
    var totalPrice: Double
) {
    companion object {
        fun fromInternal(source: OrderLine): OrderLineDTO {
            return OrderLineDTO(
                shopifyId = source.shopifyId,
                sku = source.sku,
                title = source.title,
                refunded = source.refunded,
                restockType = source.restockType,
                productId = source.productId,
                variantId = source.variantId,
                variantTitle = source.variantTitle,
                quantity = source.quantity,
                price = source.price,
                totalDiscount = source.totalDiscount,
                totalTax = source.totalTax,
                totalPrice = source.totalPrice
            )
        }
    }
}
