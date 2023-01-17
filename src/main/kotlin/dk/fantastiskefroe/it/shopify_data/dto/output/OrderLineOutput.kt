package dk.fantastiskefroe.it.shopify_data.dto.output

import dk.fantastiskefroe.it.shopify_data.entity.OrderLine
import dk.fantastiskefroe.it.shopify_data.entity.RestockType
import org.eclipse.microprofile.openapi.annotations.media.Schema

data class OrderLineOutput(
    @field:Schema(required = true)
    val shopifyId: Long,

    @field:Schema(required = true)
    val sku: String,

    @field:Schema(required = true)
    val title: String,

    @field:Schema(required = true)
    val productId: Long,

    @field:Schema(required = true)
    val variantId: Long,

    @field:Schema(required = false)
    val variantTitle: String?,

    @field:Schema(required = true)
    val refunded: Boolean,

    @field:Schema(required = true)
    val restockType: RestockType,

    @field:Schema(required = true)
    val quantity: Int,

    @field:Schema(required = true)
    val price: Double,

    @field:Schema(required = true)
    var totalDiscount: Double,

    @field:Schema(required = true)
    var totalTax: Double,

    @field:Schema(required = true)
    var totalPrice: Double
) {
    companion object {
        fun fromInternal(source: OrderLine): OrderLineOutput {
            return OrderLineOutput(
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
