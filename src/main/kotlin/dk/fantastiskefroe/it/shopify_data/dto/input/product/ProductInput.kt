package dk.fantastiskefroe.it.shopify_data.dto.input.product

import org.eclipse.microprofile.openapi.annotations.media.Schema
import java.time.Instant

data class ProductInput(
    @field:Schema(required = true)
    val id: Long,

    @field:Schema(required = true)
    val title: String,

    @field:Schema(required = true)
    val handle: String,

    @field:Schema(required = true)
    val status: ProductStatusInput,

    @field:Schema(required = false)
    val publishedAt: Instant?,

    @field:Schema(required = true)
    val images: List<ProductImageInput>,

    @field:Schema(required = true)
    val tags: String,

    @field:Schema(required = true)
    val variants: List<ProductVariantInput>,
)
