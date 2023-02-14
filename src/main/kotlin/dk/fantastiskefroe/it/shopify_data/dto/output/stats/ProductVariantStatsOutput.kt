package dk.fantastiskefroe.it.shopify_data.dto.output.stats

import dk.fantastiskefroe.it.shopify_data.dto.output.product.ProductOutput
import dk.fantastiskefroe.it.shopify_data.dto.output.product.ProductVariantOutput
import dk.fantastiskefroe.it.shopify_data.entity.stats.ProductVariantStats
import org.eclipse.microprofile.openapi.annotations.media.Schema

data class ProductVariantStatsOutput(
    @field:Schema(required = true)
    val product: ProductOutput,

    @field:Schema(required = true)
    val variant: ProductVariantOutput,

    @field:Schema(required = true)
    val numOrders: Int,

    @field:Schema(required = true)
    val numSold: Int,

    @field:Schema(required = true)
    val soldPerDay: Double,

    @field:Schema(required = true)
    val daysLeft: Double,
) {
    companion object {
        fun fromInternal(source: ProductVariantStats): ProductVariantStatsOutput {
            return ProductVariantStatsOutput(
                product = ProductOutput.fromInternal(source.product),
                variant = ProductVariantOutput.fromInternal(source.variant),
                numOrders = source.numOrders,
                numSold = source.numSold,
                soldPerDay = source.soldPerDay,
                daysLeft = source.daysLeft
            )
        }
    }
}

