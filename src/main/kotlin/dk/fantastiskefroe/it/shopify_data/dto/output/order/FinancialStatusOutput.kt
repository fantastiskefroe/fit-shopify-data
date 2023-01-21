package dk.fantastiskefroe.it.shopify_data.dto.output.order

import dk.fantastiskefroe.it.shopify_data.entity.order.FinancialStatus

enum class FinancialStatusOutput {
    NULL, PENDING, AUTHORIZED, PARTIALLY_PAID, PAID, PARTIALLY_REFUNDED, REFUNDED, VOIDED;

    companion object {
        fun fromInternal(source: FinancialStatus): FinancialStatusOutput {
            return when (source) {
                FinancialStatus.NULL -> NULL
                FinancialStatus.PENDING -> PENDING
                FinancialStatus.AUTHORIZED -> AUTHORIZED
                FinancialStatus.PARTIALLY_PAID -> PARTIALLY_PAID
                FinancialStatus.PAID -> PAID
                FinancialStatus.PARTIALLY_REFUNDED -> PARTIALLY_REFUNDED
                FinancialStatus.REFUNDED -> REFUNDED
                FinancialStatus.VOIDED -> VOIDED
            }
        }
    }
}
