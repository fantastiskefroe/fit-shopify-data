package dk.fantastiskefroe.it.shopify_data.controller

import dk.fantastiskefroe.it.shopify_data.dto.output.order.OrderOutput
import dk.fantastiskefroe.it.shopify_data.entity.*
import dk.fantastiskefroe.it.shopify_data.entity.order.FulfillmentStatus
import dk.fantastiskefroe.it.shopify_data.service.OrderService
import java.time.Instant
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType


@Path("/orders")
class OrderController @Inject constructor(val orderService: OrderService) {

    @HeaderParam("x-fit-token")
    var token: String? = null

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    fun getOrders(
        @QueryParam("from") from: Instant,
        @QueryParam("to") to: Instant,
        @QueryParam("fulfillmentStatus") fulfillmentStatus: FulfillmentStatus?
    ): List<OrderOutput> {
        return orderService.getOrders(from, to, fulfillmentStatus)
            .map(OrderOutput::fromInternal)
    }
}
