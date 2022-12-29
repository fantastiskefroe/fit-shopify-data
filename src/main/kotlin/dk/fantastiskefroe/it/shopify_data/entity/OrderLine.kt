package dk.fantastiskefroe.it.shopify_data.entity

import javax.persistence.Entity

@Entity(name = "order_lines")
class OrderLine : PanachePostgresEntity() {
    lateinit var sku: String
    lateinit var title: String
    var variantTitle: String? = null
    var quantity: Int = 0
    var price: Double? = null
}
