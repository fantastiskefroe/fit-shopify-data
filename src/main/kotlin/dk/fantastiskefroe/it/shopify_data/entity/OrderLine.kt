package dk.fantastiskefroe.it.shopify_data.entity

import javax.persistence.Entity

@Entity(name = "order_lines")
class OrderLine : PanachePostgresEntity() {
    var shopifyId: Long? = null
    lateinit var sku: String
    lateinit var title: String
    var productId: Long? = null
    var variantId: Long? = null
    var variantTitle: String? = null
    var quantity: Int = 0
    var price: Double = 0.0
    var totalDiscount: Double = 0.0
    var totalTax: Double = 0.0
    var totalPrice: Double = 0.0
    var refunded: Boolean = false
    lateinit var restockType: RestockType
}
