package dk.fantastiskefroe.it.shopify_data.service

import dk.fantastiskefroe.it.shopify_data.entity.order.CancelReason
import dk.fantastiskefroe.it.shopify_data.entity.order.Order
import dk.fantastiskefroe.it.shopify_data.entity.order.OrderLine
import dk.fantastiskefroe.it.shopify_data.entity.stats.Stats
import java.time.Instant
import java.time.temporal.ChronoUnit
import javax.enterprise.context.ApplicationScoped


@ApplicationScoped
class StatsService {

    fun getStats(from: Instant, to: Instant): Stats {
        val orders = Order.listValidByCreatedDateTime(from, to)
            .filter { it.cancelReason == CancelReason.NULL }

        return statsFromOrderList(orders)
    }

    fun getStatsByDay(from: Instant, to: Instant): List<Stats> {
        val orders = Order.listValidByCreatedDateTime(from, to)
            .filter { it.cancelReason == CancelReason.NULL }

        return orders
            .groupBy { it.createdDateTime.truncatedTo(ChronoUnit.DAYS) }
            .values
            .map(::statsFromOrderList)
    }

    private fun statsFromOrderList(orders: List<Order>): Stats {
        val weights = orders.map(Order::weight)

        return Stats(
            orders.minOf(Order::createdDateTime),
            orders.maxOf(Order::createdDateTime),
            orders.size,
            orders.map(Order::totalPrice).average(),
            orders.map(Order::totalPrice).sum(),
            if (weights.contains(null)) null else weights.requireNoNulls().average(),
            if (weights.contains(null)) null else weights.requireNoNulls().sum(),
            orders.flatMap(Order::orderLines).size,
            orders.flatMap(Order::orderLines).map(OrderLine::price).average(),
            orders.flatMap(Order::orderLines).map(OrderLine::price).sum(),
        )
    }
}
