package dk.fantastiskefroe.it.shopify_data.entity

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity(name = "order_lines")
class OrderLine : PanachePostgresEntity() {
    var shopifyId: Long = 0
    lateinit var sku: String
    lateinit var title: String
    var productId: Long = 0
    var variantId: Long = 0
    var variantTitle: String? = null
    var quantity: Int = 0
    var price: Double = 0.0
    var totalDiscount: Double = 0.0
    var totalTax: Double = 0.0
    var totalPrice: Double = 0.0
    var refunded: Boolean = false

    @Enumerated(EnumType.STRING)
    lateinit var restockType: RestockType
}
