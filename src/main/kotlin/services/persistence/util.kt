package services.persistence

import services.extensions.setText
import javax.swing.text.Document
import javax.swing.text.PlainDocument

internal fun plainDocumentOfGapContent(init: String): Document {
    val plainDocument = PlainDocument()
    plainDocument.setText(init)
    return plainDocument
}

internal val methodsList: List<String> = listOf(
    "GET", "HEAD", "POST", "PUT",
    "DELETE", "OPTIONS", "TRACE", "PATCH"
)
