package dk.fantastiskefroe.it.shopify_data.dto.output.product

import dk.fantastiskefroe.it.shopify_data.entity.product.ProductVariant
import org.eclipse.microprofile.openapi.annotations.media.Schema
import java.time.Instant

data class ProductVariantOutput(
    @field:Schema(required = true)
    val shopifyId: Long,

    @field:Schema(required = false)
    val title: String?,

    @field:Schema(required = true)
    val sku: String,

    @field:Schema(required = true)
    val inventoryQuantity: Int,

    @field:Schema(required = true)
    val weight: Int,

    @field:Schema(required = true)
    val price: Double,

    @field:Schema(required = false)
    val compareAtPrice: Double?,

    @field:Schema(required = true)
    val createdDateTime: Instant,

    @field:Schema(required = true)
    val updatedDateTime: Instant,
) {
    companion object {
        fun fromInternal(source: ProductVariant): ProductVariantOutput {
            return ProductVariantOutput(
                shopifyId = source.shopifyId,
                title = source.title,
                sku = source.sku,
                inventoryQuantity = source.inventoryQuantity,
                weight = source.weight,
                price = source.price,
                compareAtPrice = source.compareAtPrice,
                createdDateTime = source.createdDateTime,
                updatedDateTime = source.updatedDateTime
            )
        }
    }
}