package dk.fantastiskefroe.it.shopify_data.service

import dk.fantastiskefroe.it.shopify_data.entity.order.CancelReason
import dk.fantastiskefroe.it.shopify_data.entity.order.Order
import dk.fantastiskefroe.it.shopify_data.entity.order.OrderLine
import dk.fantastiskefroe.it.shopify_data.entity.product.Product
import dk.fantastiskefroe.it.shopify_data.entity.product.ProductVariant
import dk.fantastiskefroe.it.shopify_data.entity.stats.GroupByUnit
import dk.fantastiskefroe.it.shopify_data.entity.stats.ProductVariantStats
import dk.fantastiskefroe.it.shopify_data.entity.stats.Stats
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject


@ApplicationScoped
class StatsService @Inject constructor(val productService: ProductService) {

    fun getStats(from: Instant, to: Instant): Stats {
        val orders = Order.listValidByCreatedDateTime(from, to)
            .filter { it.cancelReason == CancelReason.NULL }

        return statsFromOrderList(orders)
    }

    fun getStatsGroupedBy(from: Instant, to: Instant, groupByUnit: GroupByUnit): List<Stats> {
        val orders = Order.listValidByCreatedDateTime(from, to)
            .filter { it.cancelReason == CancelReason.NULL }

        return orders
            .groupBy { truncateInstant(it.createdDateTime, groupByUnit) }
            .values
            .map(StatsService::statsFromOrderList)
    }

    fun getProductVariantStats(from: Instant): List<ProductVariantStats> {
        val to = Instant.now()
        val orders = Order.listValidByCreatedDateTime(from, to)
            .filter { it.cancelReason == CancelReason.NULL }

        val products: Set<Product> = orders
            .flatMap(Order::orderLines)
            .asSequence()
            .filter { orderLine -> !orderLine.refunded }
            .map(OrderLine::productId)
            .distinct()
            .mapNotNull(productService::getProductByShopifyId)
            .toSet()

        val numDays = ChronoUnit.SECONDS.between(from, to) / 86400.0

        return products
            .flatMap { product ->
                product.variants
                    .mapNotNull { productVariant ->
                        buildProductVariantStats(product, productVariant, orders, numDays)
                    }
            }
            .sortedBy(ProductVariantStats::daysLeft)
    }

    companion object {
        private fun buildProductVariantStats(
            product: Product,
            productVariant: ProductVariant,
            orders: List<Order>,
            numDays: Double
        ): ProductVariantStats? {
            val variantOrders = orders
                .filter { order -> order.orderLines.any { orderLine -> orderLine.variantId == productVariant.shopifyId } }
            if (variantOrders.isEmpty()) {
                return null
            }

            val orderLines = variantOrders
                .flatMap(Order::orderLines)
                .filter { orderLine -> orderLine.variantId == productVariant.shopifyId }
            val numSold = orderLines.sumOf(OrderLine::quantity)
            val soldPerDay = numSold / numDays
            val daysLeft = productVariant.inventoryQuantity / soldPerDay

            return ProductVariantStats(
                product,
                productVariant,
                variantOrders.size,
                numSold,
                soldPerDay,
                daysLeft,
            )
        }

        private fun statsFromOrderList(orders: List<Order>): Stats {
            val weights = orders.map(Order::weight)

            return Stats(
                orders.minOf(Order::createdDateTime),
                orders.maxOf(Order::createdDateTime),
                orders.size,
                orders.map(Order::totalPrice).average(),
                orders.map(Order::totalPrice).sum(),
                orders.map(Order::totalShippingPrice).average(),
                orders.map(Order::totalShippingPrice).sum(),
                if (weights.contains(null)) null else weights.requireNoNulls().average(),
                if (weights.contains(null)) null else weights.requireNoNulls().sum(),
                orders.flatMap(Order::orderLines).filter { !it.refunded }.size,
                orders.flatMap(Order::orderLines).filter { !it.refunded }.map(OrderLine::price).average(),
                orders.flatMap(Order::orderLines).filter { !it.refunded }.map(OrderLine::price).sum(),
            )
        }

        private val zoneId = ZoneId.of("Europe/Copenhagen")

        private fun truncateInstant(instant: Instant, groupByUnit: GroupByUnit): Instant {
            return when (groupByUnit) {
                GroupByUnit.HOURLY -> instant.truncatedTo(ChronoUnit.HOURS)
                GroupByUnit.DAILY -> instant.truncatedTo(ChronoUnit.DAYS)
                GroupByUnit.MONTHLY ->
                    ZonedDateTime.ofInstant(instant, zoneId)
                        .with(TemporalAdjusters.firstDayOfMonth()).truncatedTo(ChronoUnit.DAYS).toInstant()

                GroupByUnit.YEARLY ->
                    ZonedDateTime.ofInstant(instant, zoneId)
                        .with(TemporalAdjusters.firstDayOfYear()).truncatedTo(ChronoUnit.DAYS).toInstant()
            }
        }
    }
}
