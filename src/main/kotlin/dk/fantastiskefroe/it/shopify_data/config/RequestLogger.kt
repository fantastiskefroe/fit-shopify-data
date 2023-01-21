package dk.fantastiskefroe.it.shopify_data.config

import io.quarkus.logging.Log
import io.vertx.core.http.HttpServerRequest
import org.jboss.resteasy.reactive.server.ServerRequestFilter
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.core.UriInfo


@ApplicationScoped
class RequestLogger {
    @ServerRequestFilter(priority = 0)
    fun logBodyFilter(info: UriInfo, request: HttpServerRequest, ctx: ContainerRequestContext) {
        Log.info("Request: ${request.method()} ${request.uri()}")
        Log.info("--Request Headers--")

        ("\n" + request.headers().joinToString("\n")).let(Log::info)
        request.body { buffer ->
            Log.info("Request body: ${buffer.result()}")
        }
    }
}
