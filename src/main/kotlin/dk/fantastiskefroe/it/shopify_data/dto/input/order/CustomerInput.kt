package dk.fantastiskefroe.it.shopify_data.dto.input.order

import org.eclipse.microprofile.openapi.annotations.media.Schema

data class CustomerInput(
    @field:Schema(required = true)
    val id: Long,
)
