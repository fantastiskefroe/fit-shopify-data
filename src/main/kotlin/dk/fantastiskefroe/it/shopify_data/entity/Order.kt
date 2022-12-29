package dk.fantastiskefroe.it.shopify_data.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import java.time.Instant
import javax.persistence.*

@Entity(name = "orders")
class Order : PanachePostgresEntity() {
    lateinit var name: String
    var number: Int = 0
    var totalDiscount: Double? = null
    var subtotalPrice: Double? = null
    var totalTax: Double? = null
    var totalPrice: Double? = null
    var totalShippingPrice: Double? = null

    @Enumerated(EnumType.STRING)
    var cancelReason: CancelReason? = null

    @Enumerated(EnumType.STRING)
    lateinit var financialStatus: FinancialStatus

    @Enumerated(EnumType.STRING)
    lateinit var fulfillmentStatus: FulfillmentStatus
    lateinit var createdDateTime: Instant
    lateinit var validFrom: Instant
    var validTo: Instant? = null

    @OneToMany(
        targetEntity = OrderLine::class,
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY,
        orphanRemoval = true
    )
    @JoinColumn(name = "orderId", nullable = false)
    lateinit var orderLines: Set<OrderLine>

    companion object : PanacheCompanion<Order> {
        fun findValidByName(name: String) =
            find("name = ?1 and valid_from <= ?2 and valid_to = null", name, Instant.now()).firstResult()
        fun findByFulfillmentStatus(status: FulfillmentStatus) = list("fulfillment_status = ?1", status.name)
        fun listAllValid() = list("valid_to = null")
    }
}
