package dk.fantastiskefroe.it.shopify_data.dto.input.order

import dk.fantastiskefroe.it.shopify_data.entity.order.CancelReason

enum class CancelReasonInput {
    CUSTOMER, FRAUD, INVENTORY, DECLINED, OTHER
}

fun CancelReasonInput?.toInternal(): CancelReason {
    return when (this) {
        CancelReasonInput.CUSTOMER -> CancelReason.CUSTOMER
        CancelReasonInput.FRAUD -> CancelReason.FRAUD
        CancelReasonInput.INVENTORY -> CancelReason.INVENTORY
        CancelReasonInput.DECLINED -> CancelReason.DECLINED
        CancelReasonInput.OTHER -> CancelReason.OTHER
        null -> CancelReason.NULL
    }
}
