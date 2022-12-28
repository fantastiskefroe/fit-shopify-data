package dk.fantastiskefroe.it.shopify_data.dto

import com.fasterxml.jackson.annotation.JsonProperty

enum class FinancialStatus {
    @JsonProperty("pending")
    PENDING,
    @JsonProperty("authorized")
    AUTHORIZED,
    @JsonProperty("partially_paid")
    PARTIALLY_PAID,
    @JsonProperty("paid")
    PAID,
    @JsonProperty("partially_refunded")
    PARTIALLY_REFUNDED,
    @JsonProperty("refunded")
    REFUNDED,
    @JsonProperty("voided")
    VOIDED,
}