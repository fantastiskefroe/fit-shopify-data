package dk.fantastiskefroe.it.shopify_data.config

import io.vertx.core.http.HttpServerRequest
import org.jboss.resteasy.reactive.RestResponse
import org.jboss.resteasy.reactive.server.ServerRequestFilter
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.core.UriInfo


@ApplicationScoped
class TokenRequestFilter {

    val token = "test"

    @ServerRequestFilter(priority = 1)
    fun filter(
        info: UriInfo,
        request: HttpServerRequest,
        ctx: ContainerRequestContext
    ): RestResponse<Void>? {
        val headerToken = request.getHeader("x-fit-token")
        if (token != headerToken) {
            return RestResponse.status(RestResponse.Status.FORBIDDEN);
        }

        return null
    }
}
