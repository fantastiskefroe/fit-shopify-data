package dk.fantastiskefroe.it.shopify_data.config

import org.jboss.resteasy.reactive.RestResponse
import org.jboss.resteasy.reactive.server.ServerExceptionMapper

class ExceptionMapperConfig {
    @ServerExceptionMapper
    fun mapException(e: Exception): RestResponse<String> {
        println(e.cause)
        return RestResponse.ok()
    }
}