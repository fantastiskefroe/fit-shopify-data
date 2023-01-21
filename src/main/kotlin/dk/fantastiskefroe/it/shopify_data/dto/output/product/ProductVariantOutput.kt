package dk.fantastiskefroe.it.shopify_data.dto.output.product

import org.eclipse.microprofile.openapi.annotations.media.Schema

data class ProductVariantOutput(
    @field:Schema(required = true)
    val shopifyId: Long,

    @field:Schema(required = true)
    val title: String,

    @field:Schema(required = true)
    val sku: String,

    @field:Schema(required = true)
    val inventoryQuantity: Int,

    @field:Schema(required = true)
    val grams: Int,

    @field:Schema(required = true)
    val price: Double,

    @field:Schema(required = false)
    val compareAtPrice: Double?,
)
