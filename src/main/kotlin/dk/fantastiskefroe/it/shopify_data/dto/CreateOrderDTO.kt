package dk.fantastiskefroe.it.shopify_data.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import dk.fantastiskefroe.it.shopify_data.entity.*
import java.time.Instant

@JsonIgnoreProperties(ignoreUnknown = true)
data class CreateOrderDTO(
    val name: String,
    val number: Int,
    @JsonProperty("cancel_reason") val cancelReason: CancelReason,
    @JsonProperty("financial_status") val financialStatus: FinancialStatus,
    @JsonProperty("fulfillment_status") val fulfillmentStatus: FulfillmentStatus,
    @JsonProperty("total_discount") val totalDiscount: Double?,
    @JsonProperty("subtotal_price") val subtotalPrice: Double?,
    @JsonProperty("total_tax") val totalTax: Double?,
    @JsonProperty("total_price") val totalPrice: Double?,
    @JsonProperty("total_shipping_price") val totalShippingPrice: Double?,
    @JsonProperty("created_at") val createdAt: Instant,

    @JsonProperty("line_items") val lineItems: List<CreateOrderLineDTO>,
)

fun CreateOrderDTO.toOrder() : Order {
    return Order().also { order ->
        order.name = name
        order.number = number
        order.cancel_reason = cancelReason
        order.financial_status = financialStatus
        order.fulfillment_status = fulfillmentStatus
        order.total_discount = totalDiscount
        order.subtotal_price = subtotalPrice
        order.total_tax = totalTax
        order.total_price = totalPrice
        order.total_shipping_price = totalShippingPrice
        order.created_date_time = createdAt
        order.valid_from = Instant.now()
        order.lines = lineItems.map { line -> line.toOrderLine(order) }.toSet()
    }
}
