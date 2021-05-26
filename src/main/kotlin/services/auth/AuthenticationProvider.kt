package services.auth

import java.net.http.HttpRequest

interface AuthenticationProvider {
    fun authenticate(httpRequest: HttpRequest): HttpRequest
}
