package dk.fantastiskefroe.it.shopify_data.entity.stats

import java.time.Instant

data class Stats(
    val from: Instant,
    val to: Instant,
    val numOrders: Int,
    val averageOrderValue: Double,
    val totalOrderValue: Double,
    val averageShippingValue: Double,
    val totalShippingValue: Double,
    val averageOrderWeight: Double?,
    val totalOrderWeight: Int?,
    val numOrderLines: Int,
    val averageOrderLineValue: Double,
    val totalOrderLineValue: Double
)
