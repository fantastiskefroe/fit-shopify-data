package dk.fantastiskefroe.it.shopify_data.dto.webhook

import com.fasterxml.jackson.annotation.JsonProperty
import dk.fantastiskefroe.it.shopify_data.entity.*
import java.time.Instant

data class OrderDTO(
    val number: Int,
    val name: String,
    @JsonProperty("cancel_reason")
    val cancelReason: CancelReasonDTO?,
    @JsonProperty("financial_status")
    val financialStatus: FinancialStatusDTO?,
    @JsonProperty("fulfillment_status")
    val fulfillmentStatus: FulfillmentStatusDTO?,
    @JsonProperty("current_total_discounts_set")
    val currentTotalDiscountsSet: PriceSetDTO,
    @JsonProperty("current_subtotal_price_set")
    val currentSubtotalPriceSet: PriceSetDTO,
    @JsonProperty("current_total_tax_set")
    val currentTotalTaxSet: PriceSetDTO,
    @JsonProperty("total_shipping_price_set")
    val totalShippingPriceSet: PriceSetDTO,
    @JsonProperty("total_price_set")
    val totalPriceSet: PriceSetDTO,
    @JsonProperty("created_at")
    val createdAt: Instant,
    @JsonProperty("line_items")
    val lineItems: List<OrderLineDTO>,
)

fun OrderDTO.toInternal(): Order {
    return Order().also { order ->
        order.number = number
        order.name = name
        order.cancelReason = cancelReason.toInternal()
        order.financialStatus = financialStatus.toInternal()
        order.fulfillmentStatus = fulfillmentStatus.toInternal()
        order.totalDiscount = currentTotalDiscountsSet.shopMoney.amount
        order.subtotalPrice = currentSubtotalPriceSet.shopMoney.amount
        order.totalTax = currentTotalTaxSet.shopMoney.amount
        order.totalShippingPrice = totalShippingPriceSet.shopMoney.amount
        order.totalPrice = totalPriceSet.shopMoney.amount
        order.createdDateTime = createdAt
        order.validFrom = Instant.now()
        order.orderLines = lineItems.map(OrderLineDTO::toInternal).toSet()
    }
}
