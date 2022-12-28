package dk.fantastiskefroe.it.shopify_data.entity

import dk.fantastiskefroe.it.shopify_data.dto.CancelReason
import dk.fantastiskefroe.it.shopify_data.dto.FinancialStatus
import dk.fantastiskefroe.it.shopify_data.dto.FulfillmentStatus
import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import java.time.Instant
import javax.persistence.*

@Entity(name = "orders")
open class Order : PanachePostgresEntity() {
    lateinit var name: String
    var number: Int = 0

    var total_discount: Double? = null
    var subtotal_price: Double? = null
    var total_tax: Double? = null
    var total_price: Double? = null
    var total_shipping_price: Double? = null
    @Enumerated(EnumType.STRING)
    var cancel_reason: CancelReason? = null
    @Enumerated(EnumType.STRING)
    lateinit var financial_status: FinancialStatus
    @Enumerated(EnumType.STRING)
    lateinit var fulfillment_status: FulfillmentStatus
    lateinit var created_date_time: Instant
    lateinit var valid_from: Instant
    var valid_to: Instant? = null


    @OneToMany(mappedBy = "order", targetEntity = OrderLine::class , cascade = [ CascadeType.ALL ], orphanRemoval = true)
    lateinit var lines: Set<OrderLine>


    companion object : PanacheCompanion<Order> {
        fun findValidByName(name: String) = find("name = ?1 and valid_from <= ?2 and valid_to = null", name, Instant.now()).firstResult()
        fun findByFulfillmentStatus(status: FulfillmentStatus) = find("fulfillment_status", status)
        fun listAllValid() = find("valid_to = null").list()
    }
}
