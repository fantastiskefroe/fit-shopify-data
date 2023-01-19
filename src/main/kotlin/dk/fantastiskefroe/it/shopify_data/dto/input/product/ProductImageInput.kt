package dk.fantastiskefroe.it.shopify_data.dto.input.product

import org.eclipse.microprofile.openapi.annotations.media.Schema

data class ProductImageInput(
    @field:Schema(required = true)
    val position: Int,

    @field:Schema(required = true)
    val src: String,
)
