package dk.fantastiskefroe.it.shopify_data.dto.input

import org.eclipse.microprofile.openapi.annotations.media.Schema

data class MoneyInput(
    @field:Schema(required = true)
    val amount: Double,

    @field:Schema(required = true)
    val currencyCode: String,
)
