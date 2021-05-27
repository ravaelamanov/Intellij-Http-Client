package ui.toolWindow.request.auth

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.CCFlags
import com.intellij.ui.layout.panel
import services.persistence.BearerAuthPersistenceService
import ui.toolWindow.request.RequestTabbedPane
import javax.swing.JComponent
import javax.swing.JTextField
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

class BearerPane : RequestTabbedPane, JComponent() {
    override fun createPanel(): DialogPanel = panel() {
        val token = JTextField()
        token.document.addDocumentListener(object : DocumentListener {
            override fun insertUpdate(e: DocumentEvent?) {
                updateState()
            }

            override fun removeUpdate(e: DocumentEvent?) {
                updateState()
            }

            override fun changedUpdate(e: DocumentEvent?) {
                updateState()
            }

            private fun updateState() {
                BearerAuthPersistenceService.instance.objState.token = token.text
            }
        })
        row("Token:") { token().constraints(CCFlags.growY) }
    }
}
