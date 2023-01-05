package dk.fantastiskefroe.it.shopify_data.dto.webhook

data class RefundDTO (
    val refundLineItems: List<RefundLineItemDTO>
)
