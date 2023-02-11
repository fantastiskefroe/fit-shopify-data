package dk.fantastiskefroe.it.shopify_data.controller

import dk.fantastiskefroe.it.shopify_data.entity.*
import dk.fantastiskefroe.it.shopify_data.entity.stats.Stats
import dk.fantastiskefroe.it.shopify_data.service.StatsService
import java.time.Instant
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType


@Path("/stats")
class StatsController @Inject constructor(val statsService: StatsService) {

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    fun getStats(
        @QueryParam("from") from: Instant,
        @QueryParam("to") to: Instant,
    ): Stats {
        return statsService.getStats(from, to)
    }

    @GET
    @Path("/daily")
    @Produces(MediaType.APPLICATION_JSON)
    fun getStatsByDay(
        @QueryParam("from") from: Instant,
        @QueryParam("to") to: Instant,
    ): List<Stats> {
        return statsService.getStatsByDay(from, to)
    }
}
