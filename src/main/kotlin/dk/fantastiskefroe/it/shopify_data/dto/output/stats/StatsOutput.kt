package dk.fantastiskefroe.it.shopify_data.dto.output.stats

import dk.fantastiskefroe.it.shopify_data.entity.stats.Stats
import org.eclipse.microprofile.openapi.annotations.media.Schema
import java.time.Instant


data class StatsOutput(
    @field:Schema(required = false)
    val from: Instant?,

    @field:Schema(required = false)
    val to: Instant?,

    @field:Schema(required = true)
    val numberOfOrders: Int,

    @field:Schema(required = true)
    val averageOrderValue: Double,

    @field:Schema(required = true)
    val totalOrderValue: Double,

    @field:Schema(required = true)
    val averageShippingValue: Double,

    @field:Schema(required = true)
    val totalShippingValue: Double,

    @field:Schema(required = false)
    val averageOrderWeight: Double?,

    @field:Schema(required = true)
    val totalOrderWeight: Int?,

    @field:Schema(required = true)
    val numberOfOrderLines: Int,

    @field:Schema(required = true)
    val averageOrderLineValue: Double,

    @field:Schema(required = true)
    val totalOrderLineValue: Double,
) {
    companion object {
        fun fromInternal(source: Stats): StatsOutput {
            return StatsOutput(
                from = source.from,
                to = source.to,
                numberOfOrders = source.numOrders,
                averageOrderValue = source.averageOrderValue,
                totalOrderValue = source.totalOrderValue,
                averageShippingValue = source.averageShippingValue,
                totalShippingValue = source.totalShippingValue,
                averageOrderWeight = source.averageOrderWeight,
                totalOrderWeight = source.totalOrderWeight,
                numberOfOrderLines = source.numOrderLines,
                averageOrderLineValue = source.averageOrderLineValue,
                totalOrderLineValue = source.totalOrderLineValue
            )
        }
    }
}

