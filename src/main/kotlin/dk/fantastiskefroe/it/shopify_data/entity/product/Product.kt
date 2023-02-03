package dk.fantastiskefroe.it.shopify_data.entity.product

import dk.fantastiskefroe.it.shopify_data.entity.PanachePostgresEntity
import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import java.time.Instant
import javax.persistence.*

@Entity(name = "products")
class Product : PanachePostgresEntity() {
    var shopifyId: Long = 0
    lateinit var title: String
    lateinit var handle: String
    lateinit var mainImageUrl: String

    @Enumerated(EnumType.STRING)
    lateinit var status: ProductStatus
    lateinit var createdDateTime: Instant
    lateinit var updatedDateTime: Instant
    var publishedDateTime: Instant? = null

    @OneToMany(
        targetEntity = ProductTag::class, cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true
    )
    @JoinColumn(name = "productId", nullable = false)
    lateinit var tags: Set<ProductTag>

    @OneToMany(
        targetEntity = ProductVariant::class, cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true
    )
    @JoinColumn(name = "productId", nullable = false)
    lateinit var variants: Set<ProductVariant>

    companion object : PanacheCompanion<Product> {
        fun findByShopifyId(shopifyId: Long) = find("shopify_id = ?1", shopifyId).firstResult()
    }

//        companion object : PanacheCompanion<Order> {
//        fun findValidByNumber(number: Int) =
//            find("valid_to = null and number = ?1", number).firstResult()
//
//        fun listValidByCreatedDateTime(from: Instant, to: Instant) =
//            list(
//                "valid_to = null and created_date_time >= ?1 and created_date_time < ?2",
//                Sort.by("number"),
//                from,
//                to
//            )
//
//        fun listValidByCreatedDateTimeAndFulfillmentStatus(
//            from: Instant,
//            to: Instant,
//            fulfillmentStatus: FulfillmentStatus
//        ) =
//            list(
//                "valid_to = null and created_date_time >= ?1 and created_date_time < ?2 and fulfillment_status = ?3",
//                Sort.by("number"),
//                from,
//                to,
//                fulfillmentStatus.name
//            )
//    }
}
