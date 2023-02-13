package dk.fantastiskefroe.it.shopify_data.config

import io.vertx.core.http.HttpServerRequest
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.jboss.resteasy.reactive.RestResponse
import org.jboss.resteasy.reactive.server.ServerRequestFilter
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.container.ContainerRequestContext


@ApplicationScoped
class TokenRequestFilter(@ConfigProperty(name = "fit.token") val token: String) {

    @ServerRequestFilter(priority = 1)
    fun filter(
        request: HttpServerRequest,
        ctx: ContainerRequestContext
    ): RestResponse<Void>? {
        // Token can be sent either as a query-param or a header
        val param = request.getParam("fit-token")
        val header = request.getHeader("x-fit-token")

        if (token == param || token == header) {
            return null
        }

        return RestResponse.status(RestResponse.Status.FORBIDDEN);
    }
}
