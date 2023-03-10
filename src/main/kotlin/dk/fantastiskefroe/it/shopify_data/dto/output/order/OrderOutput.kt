package dk.fantastiskefroe.it.shopify_data.dto.output.order

import dk.fantastiskefroe.it.shopify_data.entity.order.Order
import org.eclipse.microprofile.openapi.annotations.media.Schema
import java.time.Instant

data class OrderOutput(
    @field:Schema(required = true)
    val shopifyId: Long,

    @field:Schema(required = true)
    val number: Int,

    @field:Schema(required = true)
    val name: String,

    @field:Schema(required = false)
    val customerId: Long?,

    @field:Schema(required = true)
    val cancelReason: CancelReasonOutput,

    @field:Schema(required = true)
    val financialStatus: FinancialStatusOutput,

    @field:Schema(required = true)
    val fulfillmentStatus: FulfillmentStatusOutput,

    @field:Schema(required = true)
    val subtotalPrice: Double,

    @field:Schema(required = true)
    val totalDiscount: Double,

    @field:Schema(required = true)
    val totalTax: Double,

    @field:Schema(required = true)
    val totalShippingPrice: Double,

    @field:Schema(required = true)
    val totalPrice: Double,

    @field:Schema(required = false)
    val weight: Int?,

    @field:Schema(required = true)
    val createdAt: Instant,

    @field:Schema(required = true)
    val lineItems: List<OrderLineOutput>,
) {
    companion object {
        fun fromInternal(source: Order): OrderOutput {
            return OrderOutput(
                shopifyId = source.shopifyId,
                number = source.number,
                name = source.name,
                customerId = source.customerId,
                cancelReason = CancelReasonOutput.fromInternal(source.cancelReason),
                financialStatus = FinancialStatusOutput.fromInternal(source.financialStatus),
                fulfillmentStatus = FulfillmentStatusOutput.fromInternal(source.fulfillmentStatus),
                totalDiscount = source.totalDiscount,
                subtotalPrice = source.subtotalPrice,
                totalTax = source.totalTax,
                totalPrice = source.totalPrice,
                totalShippingPrice = source.totalShippingPrice,
                weight = source.weight,
                createdAt = source.createdDateTime,
                lineItems = source.orderLines
                    .map(OrderLineOutput.Companion::fromInternal)
                    .toList()
                    .sortedBy(OrderLineOutput::sku)
            )
        }
    }
}
