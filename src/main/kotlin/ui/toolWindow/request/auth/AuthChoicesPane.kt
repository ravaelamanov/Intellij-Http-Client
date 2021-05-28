package ui.toolWindow.request.auth

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.panel
import services.persistence.RequestPanePersistenceService
import ui.toolWindow.request.RequestTabbedPane
import ui.toolWindow.util.UIProperties
import javax.swing.JComboBox
import javax.swing.JComponent
import javax.swing.border.EmptyBorder

class AuthChoicesPane : RequestTabbedPane, JComponent() {
    val authComboBox = JComboBox(RequestPanePersistenceService.instance.objState.auths)
    override fun createPanel(): DialogPanel = panel() {
        row {
            cell { authComboBox() }
        }
    }.apply {
        withBorder(
            EmptyBorder(
                UIProperties.getProperty("paddingTop").toInt(),
                UIProperties.getProperty("paddingLeft").toInt(),
                UIProperties.getProperty("paddingBottom").toInt(),
                UIProperties.getProperty("paddingRight").toInt()
            )
        )
    }
}
