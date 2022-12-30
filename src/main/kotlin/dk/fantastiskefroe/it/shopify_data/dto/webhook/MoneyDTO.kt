package dk.fantastiskefroe.it.shopify_data.dto.webhook

import com.fasterxml.jackson.annotation.JsonProperty
import dk.fantastiskefroe.it.shopify_data.entity.*

data class MoneyDTO(
    @JsonProperty("amount")
    val amount: Double,
    @JsonProperty("currency_code")
    val currencyCode: String,
)
