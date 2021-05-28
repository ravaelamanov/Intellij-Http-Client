package services.persistence

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.service
import com.intellij.util.xmlb.annotations.OptionTag
import services.persistence.converters.DocumentConverter
import javax.swing.text.Document

@Service
@State(name = "BearerAuthPersistenceService", storages = [Storage("bearerAuthPersistenceService.xml")])
class BearerAuthPersistenceService : AbstractPersistenceService<BearerAuthPersistenceService.State>() {
    companion object {
        val instance: BearerAuthPersistenceService
            get() = service()
    }

    class State {
        @OptionTag(converter = DocumentConverter::class)
        var token: Document = plainDocumentOfGapContent("")
    }

    override var objState = State()
}
