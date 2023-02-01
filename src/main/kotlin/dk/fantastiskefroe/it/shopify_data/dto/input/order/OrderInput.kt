package dk.fantastiskefroe.it.shopify_data.dto.input.order

import dk.fantastiskefroe.it.shopify_data.entity.order.Order
import org.eclipse.microprofile.openapi.annotations.media.Schema
import java.time.Instant

data class OrderInput(
    @field:Schema(required = true)
    val id: Long,

    @field:Schema(required = true)
    val number: Int,

    @field:Schema(required = true)
    val name: String,

    @field:Schema(required = false)
    val customerId: Long?,

    @field:Schema(required = false)
    val cancelReason: CancelReasonInput?,

    @field:Schema(required = false)
    val financialStatus: FinancialStatusInput?,

    @field:Schema(required = false)
    val fulfillmentStatus: FulfillmentStatusInput?,

    @field:Schema(required = true)
    val currentTotalDiscountsSet: PriceSetInput,

    @field:Schema(required = true)
    val currentSubtotalPriceSet: PriceSetInput,

    @field:Schema(required = true)
    val currentTotalTaxSet: PriceSetInput,

    @field:Schema(required = true)
    val totalShippingPriceSet: PriceSetInput,

    @field:Schema(required = true)
    val currentTotalPriceSet: PriceSetInput,

    @field:Schema(required = true)
    val totalWeight: Int,

    @field:Schema(required = true)
    val createdAt: Instant,

    @field:Schema(required = true)
    val lineItems: List<OrderLineInput>,

    @field:Schema(required = true)
    val refunds: List<RefundInput>
)

fun OrderInput.toInternal(): Order {
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
        order.weight = totalWeight
        order.createdDateTime = createdAt
        order.validFrom = Instant.now()
        order.orderLines = lineItems.map { it.toInternal(refunds) }.toSet()
    }
}
