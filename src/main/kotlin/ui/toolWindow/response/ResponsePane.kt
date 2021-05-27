package ui.toolWindow.response

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.components.JBTabbedPane
import com.intellij.ui.layout.CCFlags
import com.intellij.ui.layout.panel
import services.persistence.ResponsePanePersistenceService
import ui.toolWindow.TabbedPaneList
import java.io.FileInputStream
import java.util.*
import javax.swing.JComponent
import javax.swing.JTextField
import javax.swing.border.EmptyBorder

class ResponsePane : JComponent() {
    companion object {
        private var responseTabbedPane: JBTabbedPane = JBTabbedPane()
        private var bodyResponsePane = BodyResponsePane().createPanel()
        private var headersResponsePane = HeadersResponsePane().createPanel()
        private val tabbedPanes = TabbedPaneList()
        private val statusCode = JTextField(ResponsePanePersistenceService.instance.objState.statusCode, 4)
        private val fis = FileInputStream(
            "C:\\ifmo\\AppliedMath\\Intellij-Http-Client\\src\\main\\resources\\uiParameters.properties")
        private val prop = Properties()
        fun createResponsePane(): DialogPanel = panel() {
            prop.load(fis)
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
        }.apply {
            withBorder(EmptyBorder(
                prop.getProperty("paddingTop").toInt(),
                prop.getProperty("paddingLeft").toInt(),
                prop.getProperty("paddingBottom").toInt(),
                prop.getProperty("paddingRight").toInt())
            )
        }

        fun setBodyText(body: String) {
            BodyResponsePane.setBodyJTextArea(body)
        }

        fun setStatusCode(status: String) {
            statusCode.text = status
        }
    }
}
