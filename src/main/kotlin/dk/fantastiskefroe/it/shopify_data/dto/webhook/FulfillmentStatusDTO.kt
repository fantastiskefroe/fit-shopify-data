package dk.fantastiskefroe.it.shopify_data.dto.webhook

import dk.fantastiskefroe.it.shopify_data.entity.FulfillmentStatus

enum class FulfillmentStatusDTO {
    NULL, FULFILLED, PARTIAL, RESTOCKED, PENDING,
}

fun FulfillmentStatusDTO?.toInternal(): FulfillmentStatus {
    return when (this) {
        FulfillmentStatusDTO.NULL -> FulfillmentStatus.NULL
        FulfillmentStatusDTO.FULFILLED -> FulfillmentStatus.FULFILLED
        FulfillmentStatusDTO.PARTIAL -> FulfillmentStatus.PARTIAL
        FulfillmentStatusDTO.RESTOCKED -> FulfillmentStatus.RESTOCKED
        FulfillmentStatusDTO.PENDING -> FulfillmentStatus.PENDING
        null -> FulfillmentStatus.NULL
    }
}
