package services.auth.impl

import services.auth.AuthenticationProvider
import java.net.http.HttpRequest

class NoAuthenticationProvider : AuthenticationProvider {
    override fun authenticate(httpRequest: HttpRequest): HttpRequest {
        return httpRequest
    }
}
