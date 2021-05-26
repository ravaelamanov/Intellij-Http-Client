package services.persistence

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.service

@Service
@State(name = "BearerAuthPersistenceService", storages = [Storage("bearerAuthPersistenceService.xml")])
class BearerAuthPersistenceService : AbstractPersistenceService<BearerAuthPersistenceService.State>() {
    companion object {
        val instance: BearerAuthPersistenceService
            get() = service()
    }

    class State {
        var token: String = ""
    }

    override var objState = State()
}
