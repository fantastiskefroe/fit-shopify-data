package dk.fantastiskefroe.it.shopify_data.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.panache.common.Sort
import java.time.Instant
import javax.persistence.*

@Entity(name = "orders")
class Order : PanachePostgresEntity() {
    var number: Int = 0
    lateinit var name: String

    @Enumerated(EnumType.STRING)
    lateinit var cancelReason: CancelReason

    @Enumerated(EnumType.STRING)
    lateinit var financialStatus: FinancialStatus

    @Enumerated(EnumType.STRING)
    lateinit var fulfillmentStatus: FulfillmentStatus

    var totalDiscount: Double = 0.0
    var subtotalPrice: Double = 0.0
    var totalTax: Double = 0.0
    var totalPrice: Double = 0.0
    var totalShippingPrice: Double = 0.0

    lateinit var createdDateTime: Instant
    lateinit var validFrom: Instant
    var validTo: Instant? = null

    @OneToMany(
        targetEntity = OrderLine::class, cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true
    )
    @JoinColumn(name = "orderId", nullable = false)
    lateinit var orderLines: Set<OrderLine>

    companion object : PanacheCompanion<Order> {
        fun findValidByNumber(number: Int) =
            find("number = ?1 and valid_from <= ?2 and valid_to = null", number, Instant.now()).firstResult()

        fun listByFulfillmentStatus(status: FulfillmentStatus) =
            list("fulfillment_status = ?1 and valid_to = null", Sort.by("number"), status.name)

        fun listAllValid() = list("valid_to = null", Sort.by("number"))
    }
}
