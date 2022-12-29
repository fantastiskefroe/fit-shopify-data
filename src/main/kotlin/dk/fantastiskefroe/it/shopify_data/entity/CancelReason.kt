package dk.fantastiskefroe.it.shopify_data.entity

import com.fasterxml.jackson.annotation.JsonProperty

enum class CancelReason {
    @JsonProperty("customer")
    CUSTOMER,
    @JsonProperty("fraud")
    FRAUD,
    @JsonProperty("inventory")
    INVENTORY,
    @JsonProperty("declined")
    DECLINED,
    @JsonProperty("other")
    OTHER
}