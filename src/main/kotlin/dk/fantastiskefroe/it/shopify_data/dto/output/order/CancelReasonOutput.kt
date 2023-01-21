package dk.fantastiskefroe.it.shopify_data.dto.output.order

import dk.fantastiskefroe.it.shopify_data.entity.order.CancelReason

enum class CancelReasonOutput {
    NULL, CUSTOMER, FRAUD, INVENTORY, DECLINED, OTHER;

    companion object {
        fun fromInternal(source: CancelReason): CancelReasonOutput {
            return when (source) {
                CancelReason.NULL -> NULL
                CancelReason.CUSTOMER -> CUSTOMER
                CancelReason.FRAUD -> FRAUD
                CancelReason.INVENTORY -> INVENTORY
                CancelReason.DECLINED -> DECLINED
                CancelReason.OTHER -> OTHER
            }
        }
    }
}
