package dk.fantastiskefroe.it.shopify_data.dto

import dk.fantastiskefroe.it.shopify_data.entity.CancelReason
import dk.fantastiskefroe.it.shopify_data.entity.FinancialStatus
import dk.fantastiskefroe.it.shopify_data.entity.FulfillmentStatus
import dk.fantastiskefroe.it.shopify_data.entity.Order
import java.time.Instant

data class OrderDTO(
    val number: Int,
    val name: String,
    val cancelReason: CancelReason,
    val financialStatus: FinancialStatus,
    val fulfillmentStatus: FulfillmentStatus,
    val subtotalPrice: Double,
    val totalDiscount: Double,
    val totalTax: Double,
    val totalShippingPrice: Double,
    val totalPrice: Double,
    val createdAt: Instant,
    val lineItems: List<OrderLineDTO>,
) {
    companion object {
        fun fromInternal(source: Order): OrderDTO {
            return OrderDTO(
                number = source.number,
                name = source.name,
                cancelReason = source.cancelReason,
                financialStatus = source.financialStatus,
                fulfillmentStatus = source.fulfillmentStatus,
                totalDiscount = source.totalDiscount,
                subtotalPrice = source.subtotalPrice,
                totalTax = source.totalTax,
                totalPrice = source.totalPrice,
                totalShippingPrice = source.totalShippingPrice,
                createdAt = source.createdDateTime,
                lineItems = source.orderLines.map(OrderLineDTO::fromInternal).toList().sortedBy(OrderLineDTO::sku)
            )
        }
    }
}
