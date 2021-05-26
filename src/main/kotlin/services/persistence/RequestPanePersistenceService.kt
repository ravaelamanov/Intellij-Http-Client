package services.persistence

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.service
import com.intellij.util.xmlb.annotations.OptionTag
import services.auth.AuthenticationProvider
import services.persistence.converters.VectorConverter
import java.util.Vector

@Service
@State(name = "RequestPanePersistenceService", storages = [Storage("requestPanePersistenceService.xml")])
class RequestPanePersistenceService : AbstractPersistenceService<RequestPanePersistenceService.State>() {
    companion object {
        val instance: RequestPanePersistenceService
            get() = service()
    }

    class State {
        var method: String = "GET"
        var url: String = ""
        @OptionTag(converter = VectorConverter::class)
        var parametersKeyValueTable: Vector<Vector<String>> = Vector(1)
        @OptionTag(converter = VectorConverter::class)
        var headersKeyValueTable: Vector<Vector<String>> = Vector(1)
        var body: String = ""
        var auth = AuthenticationProvider.Strategies.No_Auth

        init {
            parametersKeyValueTable.add(Vector(2))
            headersKeyValueTable.add(Vector(2))
        }
    }

    override var objState = State()
}
