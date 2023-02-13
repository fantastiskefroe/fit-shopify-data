package dk.fantastiskefroe.it.shopify_data.entity.stats

import dk.fantastiskefroe.it.shopify_data.entity.product.Product
import dk.fantastiskefroe.it.shopify_data.entity.product.ProductVariant

data class ProductVariantStats(
    val product: Product,
    val variant: ProductVariant,
    val numOrders: Int,
    val numSold: Int,
    val soldPerDay: Double,
    val daysLeft: Double,
)
