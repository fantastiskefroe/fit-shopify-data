package dk.fantastiskefroe.it.shopify_data.entity.product

import dk.fantastiskefroe.it.shopify_data.entity.PanachePostgresEntity
import java.time.Instant
import javax.persistence.Entity

@Entity(name = "product_variants")
class ProductVariant : PanachePostgresEntity() {
    var shopifyId: Long = 0
    var title: String? = null
    lateinit var sku: String
    var inventoryQuantity: Int = 0
    var weight: Int = 0
    var price: Double = 0.0
    var compareAtPrice: Double? = null

    lateinit var createdDateTime: Instant
    lateinit var updatedDateTime: Instant
}
