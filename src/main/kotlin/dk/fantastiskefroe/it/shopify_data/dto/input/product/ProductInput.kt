package dk.fantastiskefroe.it.shopify_data.dto.input.product

import dk.fantastiskefroe.it.shopify_data.entity.product.Product
import dk.fantastiskefroe.it.shopify_data.entity.product.ProductTag
import org.eclipse.microprofile.openapi.annotations.media.Schema
import java.time.Instant

data class ProductInput(
    @field:Schema(required = true)
    val id: Long,

    @field:Schema(required = true)
    val title: String,

    @field:Schema(required = true)
    val handle: String,

    @field:Schema(required = false)
    val vendor: String?,

    @field:Schema(required = true)
    val status: ProductStatusInput,

    @field:Schema(required = true)
    val createdAt: Instant,

    @field:Schema(required = true)
    val updatedAt: Instant,

    @field:Schema(required = false)
    val publishedAt: Instant?,

    @field:Schema(required = true)
    val images: List<ProductImageInput>,

    @field:Schema(required = true)
    val tags: String,

    @field:Schema(required = true)
    val variants: List<ProductVariantInput>,
)

fun ProductInput.toInternal(): Product {
    return Product().also { product ->
        product.shopifyId = id
        product.title = title
        product.handle = handle
        product.vendor = vendor
        product.mainImageUrl = images.find { it.position == 0 }?.src ?: ""
        product.status = status.toInternal()
        product.createdDateTime = createdAt
        product.updatedDateTime = updatedAt
        product.publishedDateTime = publishedAt
        product.tags = tags.split(", ").map(String::toProductTag).toSet()
        product.variants = variants.map(ProductVariantInput::toInternal).toSet()
    }
}

fun String.toProductTag(): ProductTag {
    return ProductTag().also { productTag ->
        productTag.text = this
    }
}
