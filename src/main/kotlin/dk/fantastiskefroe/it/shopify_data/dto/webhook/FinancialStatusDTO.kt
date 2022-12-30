package dk.fantastiskefroe.it.shopify_data.dto.webhook

import dk.fantastiskefroe.it.shopify_data.entity.FinancialStatus

enum class FinancialStatusDTO {
    PENDING, AUTHORIZED, PARTIALLY_PAID, PAID, PARTIALLY_REFUNDED, REFUNDED, VOIDED,
}

fun FinancialStatusDTO?.toInternal(): FinancialStatus {
    return when (this) {
        FinancialStatusDTO.PENDING -> FinancialStatus.PENDING
        FinancialStatusDTO.AUTHORIZED -> FinancialStatus.AUTHORIZED
        FinancialStatusDTO.PARTIALLY_PAID -> FinancialStatus.PARTIALLY_PAID
        FinancialStatusDTO.PAID -> FinancialStatus.PAID
        FinancialStatusDTO.PARTIALLY_REFUNDED -> FinancialStatus.PARTIALLY_REFUNDED
        FinancialStatusDTO.REFUNDED -> FinancialStatus.REFUNDED
        FinancialStatusDTO.VOIDED -> FinancialStatus.VOIDED
        null -> FinancialStatus.NULL
    }
}
