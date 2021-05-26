package services.persistence

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.service

@Service
@State(name = "BasicAuthPersistenceService", storages = [Storage("basicAuthPersistenceService.xml")])
class BasicAuthPersistenceService : AbstractPersistenceService<BasicAuthPersistenceService.State>() {
    companion object {
        val instance: BasicAuthPersistenceService
            get() = service()
    }

    class State {
        var userName: String = ""
        var password: String = ""
    }

    override var objState = State()
}
