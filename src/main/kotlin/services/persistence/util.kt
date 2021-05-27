package services.persistence

import javax.swing.text.Document
import javax.swing.text.GapContent
import javax.swing.text.PlainDocument

internal fun plainDocumentOfGapContent(init: String): Document {
    val content = GapContent()
    content.insertString(0, init)
    return PlainDocument(content)
}
