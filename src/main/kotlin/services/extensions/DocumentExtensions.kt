package services.extensions

import javax.swing.text.Document

fun Document.allText(): String {
    return getText(0, length)
}

fun Document.setText(text: String) {
    remove(0, length)
    insertString(0, text, null)
}
