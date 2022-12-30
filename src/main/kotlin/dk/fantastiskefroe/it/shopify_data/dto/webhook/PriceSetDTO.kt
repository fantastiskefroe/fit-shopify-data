package dk.fantastiskefroe.it.shopify_data.dto.webhook

import com.fasterxml.jackson.annotation.JsonProperty

data class PriceSetDTO(
    @JsonProperty("shop_money")
    val shopMoney: MoneyDTO,
    @JsonProperty("presentment_money")
    val presentmentMoney: MoneyDTO,
)
