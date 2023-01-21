package dk.fantastiskefroe.it.shopify_data.controller

import dk.fantastiskefroe.it.shopify_data.dto.input.order.OrderInput
import dk.fantastiskefroe.it.shopify_data.dto.output.order.OrderOutput
import dk.fantastiskefroe.it.shopify_data.entity.*
import dk.fantastiskefroe.it.shopify_data.service.WebhookService
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType


@Path("/webhook")
class WebhookController @Inject constructor(val webhookService: WebhookService) {

    @POST
    @Path("/order-created")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createOrder(order: OrderInput): OrderOutput {
        return webhookService.createOrUpdateOrder(order)
            .let(OrderOutput.Companion::fromInternal)
    }

    @POST
    @Path("/order-updated")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun updateOrder(order: OrderInput): OrderOutput {
        return webhookService.createOrUpdateOrder(order)
            .let(OrderOutput.Companion::fromInternal)
    }
}
