package services.persistence.converters

import com.intellij.util.xmlb.Converter
import services.extensions.allText
import services.persistence.plainDocumentOfGapContent
import javax.swing.text.Document

class DocumentConverter : Converter<Document>() {
    override fun toString(value: Document): String {
        return value.allText()
    }

    override fun fromString(value: String): Document {
        return plainDocumentOfGapContent(value)
    }
}
