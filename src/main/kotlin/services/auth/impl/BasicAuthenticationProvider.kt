package services.auth.impl

import services.persistence.BasicAuthPersistenceService
import java.net.http.HttpRequest
import java.util.Base64

class BasicAuthenticationProvider : AbstractAuthenticationProvider() {
    override val authStrategy: String = "Basic"

    override fun authInfo(httpRequest: HttpRequest): String {
        val state = BasicAuthPersistenceService.instance.objState
        val credentials = "${state.userName}:${state.password}"
        return Base64.getEncoder().encodeToString(credentials.toByteArray())
    }
}
