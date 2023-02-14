package dk.fantastiskefroe.it.shopify_data.controller

import dk.fantastiskefroe.it.shopify_data.dto.output.product.ProductOutput
import dk.fantastiskefroe.it.shopify_data.entity.*
import dk.fantastiskefroe.it.shopify_data.service.ProductService
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType


@Path("/products")
class ProductController @Inject constructor(val productService: ProductService) {

    @HeaderParam("x-fit-token")
    var token: String? = null

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    fun getProducts(
    ): List<ProductOutput> {
        return productService.getProducts()
            .map(ProductOutput::fromInternal)
    }
}
