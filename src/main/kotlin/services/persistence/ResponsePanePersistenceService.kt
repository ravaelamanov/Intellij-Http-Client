package services.persistence

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.service
import com.intellij.util.xmlb.annotations.OptionTag
import services.persistence.converters.VectorConverter
import java.util.Vector

@Service
@State(name = "ResponsePanePersistenceService", storages = [Storage("responsePanePersistenceService.xml")])
class ResponsePanePersistenceService : AbstractPersistenceService<ResponsePanePersistenceService.State>() {
    companion object {
        val instance: ResponsePanePersistenceService
            get() = service()
    }

    class State {
        var body: String = ""
        @OptionTag(converter = VectorConverter::class)
        var headersKeyValueTable: Vector<Vector<String>> = Vector(1)
        var statusCode: String = ""
    }

    override var objState = State()
}
