package dk.fantastiskefroe.it.shopify_data.dto.input.order

import dk.fantastiskefroe.it.shopify_data.entity.order.FinancialStatus

enum class FinancialStatusInput {
    PENDING, AUTHORIZED, PARTIALLY_PAID, PAID, PARTIALLY_REFUNDED, REFUNDED, VOIDED,
}

fun FinancialStatusInput?.toInternal(): FinancialStatus {
    return when (this) {
        FinancialStatusInput.PENDING -> FinancialStatus.PENDING
        FinancialStatusInput.AUTHORIZED -> FinancialStatus.AUTHORIZED
        FinancialStatusInput.PARTIALLY_PAID -> FinancialStatus.PARTIALLY_PAID
        FinancialStatusInput.PAID -> FinancialStatus.PAID
        FinancialStatusInput.PARTIALLY_REFUNDED -> FinancialStatus.PARTIALLY_REFUNDED
        FinancialStatusInput.REFUNDED -> FinancialStatus.REFUNDED
        FinancialStatusInput.VOIDED -> FinancialStatus.VOIDED
        null -> FinancialStatus.NULL
    }
}
