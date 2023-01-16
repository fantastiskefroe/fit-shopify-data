package dk.fantastiskefroe.it.shopify_data.dto.webhook

import dk.fantastiskefroe.it.shopify_data.entity.OrderLine


data class OrderLineDTO(
    val id: Long,
    val sku: String,
    val title: String,
    val variantTitle: String?,
    val quantity: Int,
    val priceSet: PriceSetDTO,
    val totalDiscountSet: PriceSetDTO,
    val taxLines: List<TaxLineDTO>,
)

fun OrderLineDTO.toInternal(refunds: List<RefundDTO>): OrderLine {
    return OrderLine().also { orderLine ->
        orderLine.shopifyId = id
        orderLine.sku = sku
        orderLine.title = title
        orderLine.variantTitle = variantTitle
        orderLine.quantity = quantity
        orderLine.price = priceSet.shopMoney.amount
        orderLine.totalDiscount = totalDiscountSet.shopMoney.amount
        orderLine.totalTax = taxLines.sumOf { taxLine -> taxLine.priceSet.shopMoney.amount }
        orderLine.totalPrice = (orderLine.price * orderLine.quantity) - orderLine.totalDiscount

        val refundDTO =
            refunds.flatMap(RefundDTO::refundLineItems).find { it.lineItemId == id }

        orderLine.refunded = refundDTO != null
        orderLine.restockType = refundDTO?.restockType.toInternal()
    }
}
