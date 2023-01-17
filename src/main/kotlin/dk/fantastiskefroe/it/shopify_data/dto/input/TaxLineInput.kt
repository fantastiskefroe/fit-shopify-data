package dk.fantastiskefroe.it.shopify_data.dto.input

import org.eclipse.microprofile.openapi.annotations.media.Schema

data class TaxLineInput (
    @field:Schema(required = true)
    val priceSet: PriceSetInput
)
