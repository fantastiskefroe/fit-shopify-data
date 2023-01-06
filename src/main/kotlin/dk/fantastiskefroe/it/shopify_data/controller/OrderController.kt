package dk.fantastiskefroe.it.shopify_data.controller

import dk.fantastiskefroe.it.shopify_data.dto.OrderDTO
import dk.fantastiskefroe.it.shopify_data.entity.*
import java.time.Instant
import java.time.temporal.ChronoUnit
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType


@Path("/orders")
class OrderController {

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    fun getOrders(
        @QueryParam("fulfillmentStatus") fulfillmentStatus: FulfillmentStatus?,
        @QueryParam("from") fromParam: Instant? = Instant.MIN,
        @QueryParam("to") toParam: Instant? = Instant.MAX
    ): List<OrderDTO> {
        val from = fromParam ?: Instant.EPOCH
        val to = toParam ?: Instant.now().plus(365, ChronoUnit.DAYS)
        val orders = fulfillmentStatus?.let { Order.listValidByCreatedDateTimeAndFulfillmentStatus(from, to, it) }
            ?: Order.listValidByCreatedDateTime(from, to)

        return orders.map(OrderDTO::fromInternal)
    }
}
