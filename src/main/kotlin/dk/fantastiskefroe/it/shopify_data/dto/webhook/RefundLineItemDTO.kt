package dk.fantastiskefroe.it.shopify_data.dto.webhook

data class RefundLineItemDTO(
    val lineItemId: Long,
    val restockType: RestockTypeDTO
)
