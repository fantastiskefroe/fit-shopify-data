package dk.fantastiskefroe.it.shopify_data.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import javax.persistence.*

@Entity(name = "order_line")
open class OrderLine : PanachePostgresEntity() {
    companion object : PanacheCompanion<OrderLine> {
        fun findBySku(sku: String) = find("sku", sku).firstResult()
    }

    lateinit var sku: String
    lateinit var title: String
    var variant_title: String? = null
    var quantity: Int = 0
    var price: Double? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", referencedColumnName = "id", foreignKey = ForeignKey(name = "order_line_order_id_fk"))
    lateinit var order: Order
}