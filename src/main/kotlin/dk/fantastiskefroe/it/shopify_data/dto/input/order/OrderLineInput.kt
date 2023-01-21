package dk.fantastiskefroe.it.shopify_data.dto.input.order

import dk.fantastiskefroe.it.shopify_data.entity.order.OrderLine
import org.eclipse.microprofile.openapi.annotations.media.Schema


data class OrderLineInput(
    @field:Schema(required = true)
    val id: Long,

    @field:Schema(required = true)
    val sku: String,

    @field:Schema(required = true)
    val title: String,

    @field:Schema(required = true)
    val productId: Long,

    @field:Schema(required = true)
    val variantId: Long,

    @field:Schema(required = false)
    val variantTitle: String?,

    @field:Schema(required = true)
    val quantity: Int,

    @field:Schema(required = true)
    val priceSet: PriceSetInput,

    @field:Schema(required = true)
    val totalDiscountSet: PriceSetInput,

    @field:Schema(required = true)
    val taxLines: List<TaxLineInput>,
)

fun OrderLineInput.toInternal(refunds: List<RefundInput>): OrderLine {
    return OrderLine().also { orderLine ->
        orderLine.shopifyId = id
        orderLine.sku = sku
        orderLine.title = title
        orderLine.productId = productId
        orderLine.variantId = variantId
        orderLine.variantTitle = variantTitle
        orderLine.quantity = quantity
        orderLine.price = priceSet.shopMoney.amount
        orderLine.totalDiscount = totalDiscountSet.shopMoney.amount
        orderLine.totalTax = taxLines.sumOf { taxLine -> taxLine.priceSet.shopMoney.amount }
        orderLine.totalPrice = (orderLine.price * orderLine.quantity) - orderLine.totalDiscount

        val refundInput =
            refunds.flatMap(RefundInput::refundLineItems).find { it.lineItemId == id }

        orderLine.refunded = refundInput != null
        orderLine.restockType = refundInput?.restockType.toInternal()
    }
}
