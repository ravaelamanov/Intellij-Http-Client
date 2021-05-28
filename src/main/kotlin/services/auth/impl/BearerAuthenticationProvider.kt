package services.auth.impl

import services.extensions.allText
import services.persistence.BearerAuthPersistenceService
import java.net.http.HttpRequest

class BearerAuthenticationProvider : AbstractAuthenticationProvider() {
    override val authStrategy: String = "Bearer"

    override fun authInfo(httpRequest: HttpRequest): String {
        return BearerAuthPersistenceService.instance.objState.token.allText()
    }
}
