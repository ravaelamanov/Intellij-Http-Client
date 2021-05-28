package ui.toolWindow.request.auth

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.CCFlags
import com.intellij.ui.layout.panel
import services.extensions.allText
import services.persistence.BearerAuthPersistenceService
import ui.toolWindow.request.RequestTabbedPane
import ui.toolWindow.util.UIProperties
import javax.swing.JComponent
import javax.swing.JTextField

class BearerPane : RequestTabbedPane, JComponent() {
    private val objState = BearerAuthPersistenceService.instance.objState
    private val token = JTextField(
        objState.token,
        objState.token.allText(),
        UIProperties.getProperty("columnsNumberToken").toInt()
    )
    override fun createPanel(): DialogPanel = panel {
        row("Token:") {
            token().constraints(CCFlags.growY)
        }
    }
}
