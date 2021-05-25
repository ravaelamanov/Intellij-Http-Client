package ui.toolWindow.response

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.panel
import services.persistence.ResponsePanePersistenceService
import javax.swing.JComponent
import javax.swing.JTextArea

class BodyResponsePane : ResponseTabbedPane, JComponent() {
    companion object BodyText {
        private val responseText = ResponsePanePersistenceService.instance.objState.body
        private val bodyJTextArea: JTextArea = JTextArea(responseText)
        fun setBodyJTextArea(bodyText: String) {
            bodyJTextArea.text = bodyText
        }
    }
    override fun createPanel(): DialogPanel = panel() {
        bodyJTextArea.isEditable = false
        row {
            scrollPane(bodyJTextArea).constraints(pushX)
        }
    }
}
