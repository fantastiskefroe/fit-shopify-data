package dk.fantastiskefroe.it.shopify_data.controller

import dk.fantastiskefroe.it.shopify_data.dto.input.order.OrderInput
import dk.fantastiskefroe.it.shopify_data.dto.input.product.ProductInput
import dk.fantastiskefroe.it.shopify_data.dto.output.order.OrderOutput
import dk.fantastiskefroe.it.shopify_data.dto.output.product.ProductOutput
import dk.fantastiskefroe.it.shopify_data.entity.*
import dk.fantastiskefroe.it.shopify_data.service.WebhookService
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType


@Path("/webhook")
class WebhookController @Inject constructor(val webhookService: WebhookService) {

    @POST
    @Path("/order-created")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createOrder(
        @QueryParam("fit-token") token: String,
        @RequestBody order: OrderInput
    ): OrderOutput {
        return webhookService.createOrUpdateOrder(order)
            .let(OrderOutput::fromInternal)
    }

    @POST
    @Path("/order-updated")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun updateOrder(
        @QueryParam("fit-token") token: String,
        @RequestBody order: OrderInput
    ): OrderOutput {
        return webhookService.createOrUpdateOrder(order)
            .let(OrderOutput::fromInternal)
    }


    @POST
    @Path("/product-created")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createProduct(
        @QueryParam("fit-token") token: String,
        @RequestBody productInput: ProductInput
    ): ProductOutput {
        return webhookService.createOrUpdateProduct(productInput)
            .let(ProductOutput::fromInternal)
    }

    @POST
    @Path("/product-updated")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun updateProduct(
        @QueryParam("fit-token") token: String,
        @RequestBody productInput: ProductInput
    ): ProductOutput {
        return webhookService.createOrUpdateProduct(productInput)
            .let(ProductOutput::fromInternal)
    }
}
