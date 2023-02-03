package dk.fantastiskefroe.it.shopify_data.entity.product

import dk.fantastiskefroe.it.shopify_data.entity.PanachePostgresEntity
import javax.persistence.Entity

@Entity(name = "product_tags")
class ProductTag : PanachePostgresEntity() {
    lateinit var text: String
}
