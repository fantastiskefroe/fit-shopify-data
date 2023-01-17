package dk.fantastiskefroe.it.shopify_data.service

import dk.fantastiskefroe.it.shopify_data.entity.FulfillmentStatus
import dk.fantastiskefroe.it.shopify_data.entity.Order
import io.quarkus.cache.CacheResult
import java.time.Instant
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class OrderService {

    @CacheResult(cacheName = "orders")
    fun getOrders(from: Instant, to: Instant, fulfillmentStatus: FulfillmentStatus?): List<Order> {
        if (fulfillmentStatus == null) {
            return Order.listValidByCreatedDateTime(from, to)
        }

        return Order.listValidByCreatedDateTimeAndFulfillmentStatus(from, to, fulfillmentStatus)
    }
}