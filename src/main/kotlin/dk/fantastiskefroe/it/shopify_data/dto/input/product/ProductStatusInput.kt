package dk.fantastiskefroe.it.shopify_data.dto.input.product

import dk.fantastiskefroe.it.shopify_data.entity.product.ProductStatus

enum class ProductStatusInput {
    ACTIVE, ARCHIVED, DRAFT
}

fun ProductStatusInput.toInternal(): ProductStatus {
    return when (this) {
        ProductStatusInput.ACTIVE -> ProductStatus.ACTIVE
        ProductStatusInput.ARCHIVED -> ProductStatus.ARCHIVED
        ProductStatusInput.DRAFT -> ProductStatus.DRAFT
    }
}
