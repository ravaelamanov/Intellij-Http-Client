package ui.toolWindow.request.auth

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.panel
import services.persistence.RequestPanePersistenceService
import ui.toolWindow.request.RequestTabbedPane
import javax.swing.JComboBox
import javax.swing.JComponent

class AuthChoicesPane : RequestTabbedPane, JComponent() {
    val authComboBox = JComboBox(RequestPanePersistenceService.instance.objState.auths)
    override fun createPanel(): DialogPanel = panel() {
        row {
            cell { authComboBox() }
        }
    }
}
