package services.auth

import services.auth.impl.BasicAuthenticationProvider
import java.net.http.HttpRequest

interface AuthenticationProvider {
    fun authenticate(httpRequest: HttpRequest): HttpRequest

    enum class Strategies(val provider: AuthenticationProvider) {
        Basic(BasicAuthenticationProvider());
    }
}
