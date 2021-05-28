package services.persistence

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.service
import com.intellij.util.xmlb.annotations.OptionTag
import services.persistence.converters.DocumentConverter
import javax.swing.text.Document

@Service
@State(name = "BasicAuthPersistenceService", storages = [Storage("basicAuthPersistenceService.xml")])
class BasicAuthPersistenceService : AbstractPersistenceService<BasicAuthPersistenceService.State>() {
    companion object {
        val instance: BasicAuthPersistenceService
            get() = service()
    }

    class State {
        @OptionTag(converter = DocumentConverter::class)
        var userName: Document = plainDocumentOfGapContent("")
        @OptionTag(converter = DocumentConverter::class)
        var password: Document = plainDocumentOfGapContent("")
    }

    override var objState = State()
}
