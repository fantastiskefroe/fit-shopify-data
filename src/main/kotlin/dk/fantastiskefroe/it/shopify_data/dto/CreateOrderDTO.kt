package dk.fantastiskefroe.it.shopify_data.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import dk.fantastiskefroe.it.shopify_data.entity.*
import java.time.Instant

@JsonIgnoreProperties(ignoreUnknown = true)
data class CreateOrderDTO(
    val name: String,
    val number: Int,
    @JsonProperty("cancel_reason")
    val cancelReason: CancelReason?,
    @JsonProperty("financial_status")
    val financialStatus: FinancialStatus,
    @JsonProperty("fulfillment_status")
    val fulfillmentStatus: FulfillmentStatus?,
    @JsonProperty("total_discount")
    val totalDiscount: Double?,
    @JsonProperty("subtotal_price")
    val subtotalPrice: Double?,
    @JsonProperty("total_tax")
    val totalTax: Double?,
    @JsonProperty("total_price")
    val totalPrice: Double?,
    @JsonProperty("total_shipping_price")
    val totalShippingPrice: Double?,
    @JsonProperty("created_at")
    val createdAt: Instant,
    @JsonProperty("line_items")
    val lineItems: List<CreateOrderLineDTO>,
)

fun CreateOrderDTO.toOrder(): Order {
    return Order().also { order ->
        order.name = name
        order.number = number
        order.cancelReason = cancelReason
        order.financialStatus = financialStatus
        order.fulfillmentStatus = fulfillmentStatus ?: FulfillmentStatus.NULL
        order.totalDiscount = totalDiscount
        order.subtotalPrice = subtotalPrice
        order.totalTax = totalTax
        order.totalPrice = totalPrice
        order.totalShippingPrice = totalShippingPrice
        order.createdDateTime = createdAt
        order.validFrom = Instant.now()
        order.orderLines = lineItems.map(CreateOrderLineDTO::toOrderLine).toSet()
    }
}
