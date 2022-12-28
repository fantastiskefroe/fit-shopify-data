package dk.fantastiskefroe.it.shopify_data.dto

import com.fasterxml.jackson.annotation.JsonProperty

enum class FulfillmentStatus {
    @JsonProperty("null")
    NULL,
    @JsonProperty("fulfilled")
    FULFILLED,
    @JsonProperty("partial")
    PARTIAL,
    @JsonProperty("restocked")
    RESTOCKED,
    @JsonProperty("pending")
    PENDING,
}