package services.auth

import services.auth.impl.BasicAuthenticationProvider
import services.auth.impl.BearerAuthenticationProvider
import services.auth.impl.NoAuthenticationProvider
import java.net.http.HttpRequest

interface AuthenticationProvider {
    fun authenticate(httpRequest: HttpRequest): HttpRequest

    enum class Strategy(val provider: AuthenticationProvider) {
        No_Auth(NoAuthenticationProvider()),
        Basic(BasicAuthenticationProvider()),
        Bearer(BearerAuthenticationProvider());
        companion object {
            fun fromString(value: String): Strategy {
                return valueOf(value.replace(' ', '_'))
            }
        }

        override fun toString(): String {
            return super.toString().replace('_', ' ')
        }
    }
}
