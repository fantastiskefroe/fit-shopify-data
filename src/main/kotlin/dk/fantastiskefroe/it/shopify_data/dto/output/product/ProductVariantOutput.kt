package dk.fantastiskefroe.it.shopify_data.dto.output.product

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
)
