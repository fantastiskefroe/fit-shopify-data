package dk.fantastiskefroe.it.shopify_data.dto.input.product

import org.eclipse.microprofile.openapi.annotations.media.Schema

data class ProductVariantInput(
    @field:Schema(required = true)
    val id: Long,

    @field:Schema(required = true)
    val title: String,

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
)
