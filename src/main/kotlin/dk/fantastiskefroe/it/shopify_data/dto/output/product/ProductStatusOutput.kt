package dk.fantastiskefroe.it.shopify_data.dto.output.product

import dk.fantastiskefroe.it.shopify_data.entity.product.ProductStatus

enum class ProductStatusOutput {
    ACTIVE, ARCHIVED, DRAFT;

    companion object {
        fun fromInternal(source: ProductStatus): ProductStatusOutput {
            return when (source) {
                ProductStatus.ACTIVE -> ACTIVE
                ProductStatus.ARCHIVED -> ARCHIVED
                ProductStatus.DRAFT -> DRAFT
            }
        }
    }
}