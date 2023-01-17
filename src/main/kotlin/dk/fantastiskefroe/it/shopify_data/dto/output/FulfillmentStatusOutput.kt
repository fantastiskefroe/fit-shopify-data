package dk.fantastiskefroe.it.shopify_data.dto.output

import dk.fantastiskefroe.it.shopify_data.entity.FulfillmentStatus

enum class FulfillmentStatusOutput {
    NULL, FULFILLED, PARTIAL, RESTOCKED, PENDING;

    companion object {
        fun fromInternal(source: FulfillmentStatus): FulfillmentStatusOutput {
            return when (source) {
                FulfillmentStatus.NULL -> NULL
                FulfillmentStatus.FULFILLED -> FULFILLED
                FulfillmentStatus.PARTIAL -> PARTIAL
                FulfillmentStatus.RESTOCKED -> RESTOCKED
                FulfillmentStatus.PENDING -> PENDING
            }
        }
    }
}
