package dk.fantastiskefroe.it.shopify_data.dto.webhook

data class MoneyDTO(
    val amount: Double,
    val currencyCode: String,
)
