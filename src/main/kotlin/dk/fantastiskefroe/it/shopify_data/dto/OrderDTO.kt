package dk.fantastiskefroe.it.shopify_data.dto

import dk.fantastiskefroe.it.shopify_data.entity.Order
import java.time.Instant

data class OrderDTO(
    val name: String,
    val number: Int,
    val cancelReason: CancelReason?,
    val financialStatus: FinancialStatus,
    val fulfillmentStatus: FulfillmentStatus,
    val totalDiscount: Double?,
    val subtotalPrice: Double?,
    val totalTax: Double?,
    val totalPrice: Double?,
    val totalShippingPrice: Double?,
    val createdAt: Instant,
    val lineItems: List<OrderLineDTO>,
) {
    companion object {
        fun from(source: Order): OrderDTO {
            return OrderDTO(
                name = source.name,
                number = source.number,
                cancelReason = source.cancelReason,
                financialStatus = source.financialStatus,
                fulfillmentStatus = source.fulfillmentStatus,
                totalDiscount = source.totalDiscount,
                subtotalPrice = source.subtotalPrice,
                totalTax = source.totalTax,
                totalPrice = source.totalPrice,
                totalShippingPrice = source.totalShippingPrice,
                createdAt = source.createdDateTime,
                lineItems = source.orderLines.map(OrderLineDTO::from).toList().sortedBy(OrderLineDTO::sku)
            )
        }
    }
}
