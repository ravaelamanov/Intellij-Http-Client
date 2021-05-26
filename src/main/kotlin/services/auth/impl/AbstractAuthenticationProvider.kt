package services.auth.impl

import services.auth.AuthenticationProvider
import services.extensions.currentBuilder
import java.net.http.HttpRequest

abstract class AbstractAuthenticationProvider : AuthenticationProvider {
    private val authorization = "Authorization"
    protected abstract val authStrategy: String

    override fun authenticate(httpRequest: HttpRequest): HttpRequest {
        return httpRequest
            .currentBuilder()
            .header(authorization, "$authStrategy ${authInfo(httpRequest)}")
            .build()
    }

    protected abstract fun authInfo(httpRequest: HttpRequest): String
}
