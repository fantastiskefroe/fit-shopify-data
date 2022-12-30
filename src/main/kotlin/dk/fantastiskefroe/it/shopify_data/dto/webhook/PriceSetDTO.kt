package dk.fantastiskefroe.it.shopify_data.dto.webhook

import com.fasterxml.jackson.annotation.JsonProperty

data class PriceSetDTO(
    val shopMoney: MoneyDTO,
    val presentmentMoney: MoneyDTO,
)
