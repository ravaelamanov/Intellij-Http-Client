package ui.toolWindow.response

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.components.JBTabbedPane
import com.intellij.ui.layout.CCFlags
import com.intellij.ui.layout.panel
import ui.toolWindow.TabbedPaneList
import javax.swing.JComponent

class ResponsePane : JComponent() {
    companion object {
        private var responseTabbedPane: JBTabbedPane = JBTabbedPane()
        private var bodyResponsePane = BodyResponsePane().createPanel()
        private var headersResponsePane = HeadersResponsePane().createPanel()
        private val tabbedPanes = TabbedPaneList()

        fun createResponsePane(): DialogPanel = panel(title = "RESPONSE") {
            responseTabbedPane.addTab(tabbedPanes.listOfPanes[2], bodyResponsePane)
            responseTabbedPane.addTab(tabbedPanes.listOfPanes[1], headersResponsePane)

            row {
                responseTabbedPane().constraints(CCFlags.grow)
            }
        }

        fun setBodyText(body: String) {
            BodyResponsePane.setBodyJTextArea(body)
        }
    }
}
