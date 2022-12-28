package dk.fantastiskefroe.it.shopify_data

import org.jboss.resteasy.reactive.server.ServerRequestFilter
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.core.Response


@ApplicationScoped
class HmacFilter {
    @ServerRequestFilter
    fun getFilter(ctx: ContainerRequestContext): Optional<Response> {
//        return Optional.of(Response.status(403).build())

        return Optional.empty()
    }

}