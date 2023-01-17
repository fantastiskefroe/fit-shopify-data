package dk.fantastiskefroe.it.shopify_data.dto.input

import dk.fantastiskefroe.it.shopify_data.entity.FulfillmentStatus

enum class FulfillmentStatusInput {
    NULL, FULFILLED, PARTIAL, RESTOCKED, PENDING,
}

fun FulfillmentStatusInput?.toInternal(): FulfillmentStatus {
    return when (this) {
        FulfillmentStatusInput.NULL -> FulfillmentStatus.NULL
        FulfillmentStatusInput.FULFILLED -> FulfillmentStatus.FULFILLED
        FulfillmentStatusInput.PARTIAL -> FulfillmentStatus.PARTIAL
        FulfillmentStatusInput.RESTOCKED -> FulfillmentStatus.RESTOCKED
        FulfillmentStatusInput.PENDING -> FulfillmentStatus.PENDING
        null -> FulfillmentStatus.NULL
    }
}
