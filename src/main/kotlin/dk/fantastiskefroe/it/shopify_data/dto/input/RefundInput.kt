package dk.fantastiskefroe.it.shopify_data.dto.input

import org.eclipse.microprofile.openapi.annotations.media.Schema

data class RefundInput (
    @field:Schema(required = true)
    val refundLineItems: List<RefundLineItemInput>
)
