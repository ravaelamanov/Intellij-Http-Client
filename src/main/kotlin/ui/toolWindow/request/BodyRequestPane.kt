package ui.toolWindow.request

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.panel
import services.persistence.RequestPanePersistenceService
import javax.swing.JComponent
import javax.swing.JTextArea

class BodyRequestPane : RequestTabbedPane, JComponent() {
    private val bodyJTextArea = JTextArea(RequestPanePersistenceService.instance.objState.body)

    override fun createPanel(): DialogPanel = panel() {
        row {
            scrollPane(bodyJTextArea).constraints(pushX)
        }
    }
}
