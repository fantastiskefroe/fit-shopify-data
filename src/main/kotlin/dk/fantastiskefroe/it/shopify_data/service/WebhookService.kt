package dk.fantastiskefroe.it.shopify_data.service

import dk.fantastiskefroe.it.shopify_data.dto.input.order.OrderInput
import dk.fantastiskefroe.it.shopify_data.dto.input.order.toInternal
import dk.fantastiskefroe.it.shopify_data.dto.input.product.ProductInput
import dk.fantastiskefroe.it.shopify_data.dto.input.product.toInternal
import dk.fantastiskefroe.it.shopify_data.entity.order.Order
import dk.fantastiskefroe.it.shopify_data.entity.product.Product
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

    @Transactional
    @CacheInvalidateAll(cacheName = "products")
    fun createOrUpdateProduct(productInput: ProductInput): Product {
        Product.findByShopifyId(productInput.id)?.delete()

        return productInput.toInternal()
            .also(Product::persist)
    }
}
