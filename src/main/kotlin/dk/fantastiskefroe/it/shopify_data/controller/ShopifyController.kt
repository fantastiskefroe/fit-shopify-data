package dk.fantastiskefroe.it.shopify_data.controller

import dk.fantastiskefroe.it.shopify_data.dto.CreateOrderDTO
import dk.fantastiskefroe.it.shopify_data.dto.OrderDTO
import dk.fantastiskefroe.it.shopify_data.dto.toOrder
import dk.fantastiskefroe.it.shopify_data.entity.*
import java.time.Instant
import javax.transaction.Transactional
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType


@Transactional
@Path("/webhook")
class ShopifyController {

    @POST
    @Path("/order-created")
    @Produces(MediaType.APPLICATION_JSON)
    fun createOrder(order: CreateOrderDTO) : OrderDTO? {
        return createOrUpdateOrder(order)
    }

    @POST
    @Path("/order-updated")
    @Produces(MediaType.APPLICATION_JSON)
    fun updateOrder(order: CreateOrderDTO) : OrderDTO? {
        return createOrUpdateOrder(order)
    }

    private fun createOrUpdateOrder(order: CreateOrderDTO): OrderDTO? {
        Order.findValidByNumber(order.number)?.let {
            it.validTo = Instant.now()
            it.persist()
        }

        return order.toOrder()
            .also(Order::persist)
            .let(OrderDTO::from)
    }
}
