package dk.fantastiskefroe.it.shopify_data.dto.input

import org.eclipse.microprofile.openapi.annotations.media.Schema

data class RefundLineItemInput(
    @field:Schema(required = true)
    val lineItemId: Long,

    @field:Schema(required = true)
    val restockType: RestockTypeInput
)
