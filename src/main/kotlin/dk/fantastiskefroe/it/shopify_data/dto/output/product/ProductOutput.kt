package dk.fantastiskefroe.it.shopify_data.dto.output.product

import org.eclipse.microprofile.openapi.annotations.media.Schema
import java.time.Instant

data class ProductOutput(
    @field:Schema(required = true)
    val shopifyId: Long,

    @field:Schema(required = true)
    val title: String,

    @field:Schema(required = true)
    val handle: String,

    @field:Schema(required = true)
    val mainImageUrl: String,

    @field:Schema(required = true)
    val status: ProductStatusOutput,

    @field:Schema(required = true)
    val createdDateTime: Instant,

    @field:Schema(required = true)
    val updatedDateTime: Instant,

    @field:Schema(required = false)
    val publishedDateTime: Instant?,

    @field:Schema(required = true)
    val tags: List<String>,

    @field:Schema(required = true)
    val variants: List<ProductVariantOutput>,
)
