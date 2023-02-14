package dk.fantastiskefroe.it.shopify_data.controller

import dk.fantastiskefroe.it.shopify_data.dto.input.stats.GroupByUnitInput
import dk.fantastiskefroe.it.shopify_data.dto.input.stats.toInternal
import dk.fantastiskefroe.it.shopify_data.dto.output.stats.ProductVariantStatsOutput
import dk.fantastiskefroe.it.shopify_data.dto.output.stats.StatsOutput
import dk.fantastiskefroe.it.shopify_data.entity.*
import dk.fantastiskefroe.it.shopify_data.service.StatsService
import java.time.Instant
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType


@Path("/stats")
class StatsController @Inject constructor(val statsService: StatsService) {

    @HeaderParam("x-fit-token")
    var token: String? = null

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    fun getStats(
        @QueryParam("from") from: Instant,
        @QueryParam("to") to: Instant,
    ): StatsOutput {
        val stats = statsService.getStats(from, to)

        return StatsOutput.fromInternal(stats)
    }

    @GET
    @Path("/grouped")
    @Produces(MediaType.APPLICATION_JSON)
    fun getStatsGrouped(
        @QueryParam("from") from: Instant,
        @QueryParam("to") to: Instant,
        @QueryParam("unit") groupByUnitInput: GroupByUnitInput
    ): List<StatsOutput> {
        return statsService
            .getStatsGroupedBy(from, to, groupByUnitInput.toInternal())
            .map(StatsOutput::fromInternal)
    }

    @GET
    @Path("/remaining")
    @Produces(MediaType.APPLICATION_JSON)
    fun getProductVariantStats(
        @QueryParam("from") from: Instant,
    ): List<ProductVariantStatsOutput> {
        return statsService
            .getProductVariantStats(from)
            .map(ProductVariantStatsOutput::fromInternal)
    }
}
