package dk.fantastiskefroe.it.shopify_data.dto.output.order

import dk.fantastiskefroe.it.shopify_data.entity.order.FulfillmentStatus

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
