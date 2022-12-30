package dk.fantastiskefroe.it.shopify_data.controller.webhook

import dk.fantastiskefroe.it.shopify_data.dto.OrderDTO
import dk.fantastiskefroe.it.shopify_data.dto.webhook.toInternal
import dk.fantastiskefroe.it.shopify_data.entity.*
import java.time.Instant
import javax.transaction.Transactional
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType


@Transactional
@Path("/webhook")
class WebhookController {

    @POST
    @Path("/order-created")
    @Produces(MediaType.APPLICATION_JSON)
    fun createOrder(order: dk.fantastiskefroe.it.shopify_data.dto.webhook.OrderDTO): OrderDTO? {
        return createOrUpdateOrder(order)
    }

    @POST
    @Path("/order-updated")
    @Produces(MediaType.APPLICATION_JSON)
    fun updateOrder(order: dk.fantastiskefroe.it.shopify_data.dto.webhook.OrderDTO): OrderDTO? {
        return createOrUpdateOrder(order)
    }

    private fun createOrUpdateOrder(order: dk.fantastiskefroe.it.shopify_data.dto.webhook.OrderDTO): OrderDTO? {
        Order.findValidByNumber(order.number)?.let {
            it.validTo = Instant.now()
            it.persist()
        }

        return order.toInternal()
            .also(Order::persist)
            .let(OrderDTO::fromInternal)
    }
}
