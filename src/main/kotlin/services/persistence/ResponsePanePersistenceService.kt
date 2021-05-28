package services.persistence

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.service
import com.intellij.util.xmlb.annotations.OptionTag
import services.persistence.converters.DocumentConverter
import services.persistence.converters.VectorConverter
import java.util.Vector
import javax.swing.text.Document

@Service
@State(name = "ResponsePanePersistenceService", storages = [Storage("responsePanePersistenceService.xml")])
class ResponsePanePersistenceService : AbstractPersistenceService<ResponsePanePersistenceService.State>() {
    companion object {
        val instance: ResponsePanePersistenceService
            get() = service()
    }

    class State {
        @OptionTag(converter = DocumentConverter::class)
        var body: Document = plainDocumentOfGapContent("")
        @OptionTag(converter = VectorConverter::class)
        var headersKeyValueTable: Vector<Vector<String>> = Vector(1)
        @OptionTag(converter = DocumentConverter::class)
        var statusCode: Document = plainDocumentOfGapContent("")
    }

    override var objState = State()
}
