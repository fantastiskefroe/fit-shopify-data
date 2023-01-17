package dk.fantastiskefroe.it.shopify_data.controller

import dk.fantastiskefroe.it.shopify_data.dto.input.OrderInput
import dk.fantastiskefroe.it.shopify_data.dto.input.toInternal
import dk.fantastiskefroe.it.shopify_data.dto.output.OrderOutput
import dk.fantastiskefroe.it.shopify_data.entity.*
import java.time.Instant
import javax.transaction.Transactional
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType


@Transactional
@Path("/webhook")
class WebhookController {

    @POST
    @Path("/order-created")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createOrder(order: OrderInput): OrderOutput {
        return createOrUpdateOrder(order)
    }

    @POST
    @Path("/order-updated")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun updateOrder(order: OrderInput): OrderOutput {
        return createOrUpdateOrder(order)
    }

    private fun createOrUpdateOrder(order: OrderInput): OrderOutput {
        Order.findValidByNumber(order.number)?.let {
            it.validTo = Instant.now()
            it.persist()
        }

        return order.toInternal()
            .also(Order::persist)
            .let(OrderOutput::fromInternal)
    }
}
