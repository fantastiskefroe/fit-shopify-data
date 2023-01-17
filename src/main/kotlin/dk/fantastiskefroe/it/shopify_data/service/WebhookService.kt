package dk.fantastiskefroe.it.shopify_data.service

import dk.fantastiskefroe.it.shopify_data.dto.input.OrderInput
import dk.fantastiskefroe.it.shopify_data.dto.input.toInternal
import dk.fantastiskefroe.it.shopify_data.entity.Order
import io.quarkus.cache.CacheInvalidateAll
import java.time.Instant
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional


@ApplicationScoped
class WebhookService {

    @Transactional
    @CacheInvalidateAll(cacheName = "orders")
    fun createOrUpdateOrder(order: OrderInput): Order {
        Order.findValidByNumber(order.number)?.let {
            it.validTo = Instant.now()
            it.persist()
        }

        return order.toInternal()
            .also(Order::persist)
    }
}