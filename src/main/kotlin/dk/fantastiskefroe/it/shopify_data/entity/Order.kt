package dk.fantastiskefroe.it.shopify_data.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.panache.common.Sort
import java.time.Instant
import javax.persistence.*

@Entity(name = "orders")
class Order : PanachePostgresEntity() {
    var shopifyId: Long = 0
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
            find("valid_to = null and number = ?1", number).firstResult()

        fun listAllValid() =
            list(
                "valid_to = null",
                Sort.by("number")
            )

        fun listValidByFulfillmentStatus(fulfillmentStatus: FulfillmentStatus) =
            list(
                "valid_to = null and fulfillment_status = ?1",
                Sort.by("number"),
                fulfillmentStatus.name
            )

        fun listValidByCreatedDateTime(from: Instant, to: Instant) =
            list(
                "valid_to = null and created_date_time >= ?1 and created_date_time < ?2",
                Sort.by("number"),
                from,
                to
            )

        fun listValidByCreatedDateTimeAndFulfillmentStatus(from: Instant, to: Instant, fulfillmentStatus: FulfillmentStatus) =
            list(
                "valid_to = null and created_date_time >= ?1 and created_date_time < ?2 and fulfillment_status = ?3",
                Sort.by("number"),
                from,
                to,
                fulfillmentStatus.name
            )
    }
}
