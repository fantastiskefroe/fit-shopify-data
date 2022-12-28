package dk.fantastiskefroe.it.shopify_data.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import dk.fantastiskefroe.it.shopify_data.entity.*
import java.time.Instant

@JsonIgnoreProperties(ignoreUnknown = true)
data class OrderDTO(
    val name: String,
    val number: Int,
    @JsonProperty("cancel_reason") val cancelReason: CancelReason?,
    @JsonProperty("financial_status") val financialStatus: FinancialStatus,
    @JsonProperty("fulfillment_status") val fulfillmentStatus: FulfillmentStatus,
    @JsonProperty("total_discount") val totalDiscount: Double?,
    @JsonProperty("subtotal_price") val subtotalPrice: Double?,
    @JsonProperty("total_tax") val totalTax: Double?,
    @JsonProperty("total_price") val totalPrice: Double?,
    @JsonProperty("total_shipping_price") val totalShippingPrice: Double?,
    @JsonProperty("created_at") val createdAt: Instant,

    @JsonProperty("line_items") val lineItems: List<OrderLineDTO>,
) {
    companion object {
        fun from(source: Order): OrderDTO {
            return OrderDTO(
                name = source.name,
                number = source.number,
                cancelReason = source.cancel_reason,
                financialStatus = source.financial_status,
                fulfillmentStatus = source.fulfillment_status,
                totalDiscount = source.total_discount,
                subtotalPrice = source.subtotal_price,
                totalTax = source.total_tax,
                totalPrice = source.total_price,
                totalShippingPrice = source.total_shipping_price,
                createdAt = source.created_date_time,
                lineItems = source.lines.map(OrderLineDTO::from).toList().sortedBy(OrderLineDTO::sku)
            )
        }
    }
}
