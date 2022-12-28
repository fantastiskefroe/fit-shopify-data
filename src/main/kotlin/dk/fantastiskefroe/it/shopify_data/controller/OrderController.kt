package dk.fantastiskefroe.it.shopify_data.controller

import dk.fantastiskefroe.it.shopify_data.dto.OrderDTO
import dk.fantastiskefroe.it.shopify_data.entity.*
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType


@Path("/orders")
class OrderController {

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    fun getOrders() : List<OrderDTO> {
        return Order.listAllValid().map(OrderDTO::from)
    }
}
