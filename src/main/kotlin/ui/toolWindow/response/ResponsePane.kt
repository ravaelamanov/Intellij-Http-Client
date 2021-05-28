package ui.toolWindow.response

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.components.JBTabbedPane
import com.intellij.ui.layout.CCFlags
import com.intellij.ui.layout.panel
import services.extensions.allText
import services.persistence.ResponsePanePersistenceService
import ui.toolWindow.TabbedPaneList
import javax.swing.JComponent
import javax.swing.JTextField

class ResponsePane : JComponent() {
    companion object {
        private var responseTabbedPane: JBTabbedPane = JBTabbedPane()
        private var bodyResponsePane = BodyResponsePane().createPanel()
        private var headersResponsePane = HeadersResponsePane().createPanel()
        private val objState = ResponsePanePersistenceService.instance.objState
        private val tabbedPanes = TabbedPaneList()
        private val statusCode = JTextField(objState.statusCode, objState.statusCode.allText(), 4)
        fun createResponsePane(): DialogPanel = panel(title = "RESPONSE") {
            responseTabbedPane.addTab(tabbedPanes.listOfPanes[2], bodyResponsePane)
            responseTabbedPane.addTab(tabbedPanes.listOfPanes[1], headersResponsePane)
            statusCode.isEditable = false
            row {
                responseTabbedPane().constraints(CCFlags.grow)
            }
            row {
                label("status:")
                statusCode().constraints(CCFlags.growY)
            }
        }
    }
}
