package dk.fantastiskefroe.it.shopify_data.dto.output.product

import dk.fantastiskefroe.it.shopify_data.entity.product.Product
import dk.fantastiskefroe.it.shopify_data.entity.product.ProductTag
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
    val vendor: String?,

    @field:Schema(required = true)
    var productType: String?,

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
) {
    companion object {
        fun fromInternal(source: Product): ProductOutput {
            return ProductOutput(
                source.shopifyId,
                source.title,
                source.handle,
                source.vendor,
                source.productType,
                source.mainImageUrl,
                ProductStatusOutput.fromInternal(source.status),
                source.createdDateTime,
                source.updatedDateTime,
                source.publishedDateTime,
                source.tags.map(ProductTag::text).toList(),
                source.variants.map(ProductVariantOutput::fromInternal).toList()
            )
        }
    }
}