package dk.fantastiskefroe.it.shopify_data.service

import dk.fantastiskefroe.it.shopify_data.entity.product.Product
import io.quarkus.cache.CacheResult
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ProductService {

    @CacheResult(cacheName = "products")
    fun getProductByShopifyId(shopifyId: Long): Product? {
        return Product.findByShopifyId(shopifyId)
    }
}
