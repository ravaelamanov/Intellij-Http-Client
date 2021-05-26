package ui.toolWindow.request

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.panel
import services.persistence.RequestPanePersistenceService
import javax.swing.JComponent
import javax.swing.JTextArea
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

class BodyRequestPane : RequestTabbedPane, JComponent() {
    val bodyJTextArea = JTextArea(RequestPanePersistenceService.instance.objState.body)
    fun bodyUpdate() {
        bodyJTextArea.document.addDocumentListener(object : DocumentListener {

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
                RequestPanePersistenceService.instance.objState.body = bodyJTextArea.text
            }
        })
    }

    override fun createPanel(): DialogPanel = panel() {
        bodyUpdate()
        row {
            scrollPane(bodyJTextArea).constraints(pushX)
        }
    }
}
