package dk.fantastiskefroe.it.shopify_data.dto.input.product

import dk.fantastiskefroe.it.shopify_data.entity.product.ProductVariant
import org.eclipse.microprofile.openapi.annotations.media.Schema
import java.time.Instant

data class ProductVariantInput(
    @field:Schema(required = true)
    val id: Long,

    @field:Schema(required = false)
    val title: String?,

    @field:Schema(required = true)
    val sku: String,

    @field:Schema(required = true)
    val inventoryQuantity: Int,

    @field:Schema(required = true)
    val grams: Int,

    @field:Schema(required = true)
    val price: String,

    @field:Schema(required = false)
    val compareAtPrice: String?,

    @field:Schema(required = true)
    val createdAt: Instant,

    @field:Schema(required = true)
    val updatedAt: Instant,
)

fun ProductVariantInput.toInternal(): ProductVariant {
    return ProductVariant().also { productVariant ->
        productVariant.shopifyId = id
        productVariant.title = title
        productVariant.sku = sku
        productVariant.inventoryQuantity = inventoryQuantity
        productVariant.weight = grams
        productVariant.price = price.toDouble()
        productVariant.compareAtPrice = compareAtPrice?.toDouble()
        productVariant.createdDateTime = createdAt
        productVariant.updatedDateTime = updatedAt
    }
}
