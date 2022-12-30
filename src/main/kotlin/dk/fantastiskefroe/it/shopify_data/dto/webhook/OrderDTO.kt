package dk.fantastiskefroe.it.shopify_data.dto.webhook

import com.fasterxml.jackson.annotation.JsonProperty
import dk.fantastiskefroe.it.shopify_data.entity.*
import java.time.Instant

data class OrderDTO(
    val name: String,
    val number: Int,
    @JsonProperty("cancel_reason")
    val cancelReason: CancelReasonDTO?,
    @JsonProperty("financial_status")
    val financialStatus: FinancialStatusDTO?,
    @JsonProperty("fulfillment_status")
    val fulfillmentStatus: FulfillmentStatusDTO?,
    @JsonProperty("total_discount")
    val totalDiscount: Double?,
    @JsonProperty("subtotal_price")
    val subtotalPrice: Double?,
    @JsonProperty("total_tax")
    val totalTax: Double?,
    @JsonProperty("total_shipping_price_set")
    val totalShippingPriceSet: PriceSetDTO,
    @JsonProperty("total_price")
    val totalPrice: Double?,
    @JsonProperty("created_at")
    val createdAt: Instant,
    @JsonProperty("line_items")
    val lineItems: List<OrderLineDTO>,
)

fun OrderDTO.toInternal(): Order {
    return Order().also { order ->
        order.name = name
        order.number = number
        order.cancelReason = cancelReason.toInternal()
        order.financialStatus = financialStatus.toInternal()
        order.fulfillmentStatus = fulfillmentStatus.toInternal()
        order.totalDiscount = totalDiscount
        order.subtotalPrice = subtotalPrice
        order.totalTax = totalTax
        order.totalPrice = totalPrice
        order.totalShippingPrice = totalShippingPriceSet.shopMoney.amount
        order.createdDateTime = createdAt
        order.validFrom = Instant.now()
        order.orderLines = lineItems.map(OrderLineDTO::toInternal).toSet()
    }
}
