package dk.fantastiskefroe.it.shopify_data.dto.webhook

import dk.fantastiskefroe.it.shopify_data.entity.CancelReason

enum class CancelReasonDTO {
    CUSTOMER, FRAUD, INVENTORY, DECLINED, OTHER
}

fun CancelReasonDTO.toInternal(): CancelReason {
    return when (this) {
        CancelReasonDTO.CUSTOMER -> CancelReason.CUSTOMER
        CancelReasonDTO.FRAUD -> CancelReason.FRAUD
        CancelReasonDTO.INVENTORY -> CancelReason.INVENTORY
        CancelReasonDTO.DECLINED -> CancelReason.DECLINED
        CancelReasonDTO.OTHER -> CancelReason.OTHER
    }
}