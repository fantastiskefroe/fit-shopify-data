package dk.fantastiskefroe.it.shopify_data.dto.webhook

import dk.fantastiskefroe.it.shopify_data.entity.OrderLine


data class OrderLineDTO(
    val id: Int,
    val sku: String,
    val title: String,
    val variantTitle: String?,
    val quantity: Int,
    val priceSet: PriceSetDTO,
    val totalDiscountSet: PriceSetDTO,
    val taxLines: List<TaxLineDTO>,
)

fun OrderLineDTO.toInternal(refunds: List<RefundDTO>): OrderLine {
    return OrderLine().also {
        it.sku = sku
        it.title = title
        it.variantTitle = variantTitle
        it.quantity = quantity
        it.price = priceSet.shopMoney.amount
        it.totalDiscount = totalDiscountSet.shopMoney.amount
        it.totalTax = taxLines.sumOf { taxLine -> taxLine.priceSet.shopMoney.amount }
        it.totalPrice = it.price * quantity

        val refundDTO =
            refunds.flatMap(RefundDTO::refundLineItems).find { refundLineItemDTO -> refundLineItemDTO.lineItemId == id }

        it.refunded = refundDTO == null
        it.restockType = refundDTO?.restockType.toInternal()
    }
}
