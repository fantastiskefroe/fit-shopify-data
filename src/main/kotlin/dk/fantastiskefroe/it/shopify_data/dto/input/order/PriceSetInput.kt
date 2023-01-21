package dk.fantastiskefroe.it.shopify_data.dto.input.order

import org.eclipse.microprofile.openapi.annotations.media.Schema

data class PriceSetInput(
    @field:Schema(required = true)
    val shopMoney: MoneyInput,

    @field:Schema(required = true)
    val presentmentMoney: MoneyInput,
)
