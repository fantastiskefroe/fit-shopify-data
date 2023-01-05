package dk.fantastiskefroe.it.shopify_data.dto.webhook

import dk.fantastiskefroe.it.shopify_data.entity.*
import java.time.Instant

data class OrderDTO(
    val id: Long,
    val number: Int,
    val name: String,
    val cancelReason: CancelReasonDTO?,
    val financialStatus: FinancialStatusDTO?,
    val fulfillmentStatus: FulfillmentStatusDTO?,
    val currentTotalDiscountsSet: PriceSetDTO,
    val currentSubtotalPriceSet: PriceSetDTO,
    val currentTotalTaxSet: PriceSetDTO,
    val totalShippingPriceSet: PriceSetDTO,
    val currentTotalPriceSet: PriceSetDTO,
    val createdAt: Instant,
    val lineItems: List<OrderLineDTO>,
)

fun OrderDTO.toInternal(): Order {
    return Order().also { order ->
        order.shopifyId = id
        order.number = number
        order.name = name
        order.cancelReason = cancelReason.toInternal()
        order.financialStatus = financialStatus.toInternal()
        order.fulfillmentStatus = fulfillmentStatus.toInternal()
        order.totalDiscount = currentTotalDiscountsSet.shopMoney.amount
        order.subtotalPrice = currentSubtotalPriceSet.shopMoney.amount
        order.totalTax = currentTotalTaxSet.shopMoney.amount
        order.totalShippingPrice = totalShippingPriceSet.shopMoney.amount
        order.totalPrice = currentTotalPriceSet.shopMoney.amount
        order.createdDateTime = createdAt
        order.validFrom = Instant.now()
        order.orderLines = lineItems.map(OrderLineDTO::toInternal).toSet()
    }
}
