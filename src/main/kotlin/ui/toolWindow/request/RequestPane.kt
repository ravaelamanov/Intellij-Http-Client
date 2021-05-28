package ui.toolWindow.request

import com.intellij.openapi.components.service
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.CCFlags
import com.intellij.ui.layout.panel
import services.HttpRequestSenderService
import services.extensions.allText
import services.persistence.RequestPanePersistenceService
import ui.toolWindow.TabbedPaneList
import ui.toolWindow.request.auth.MainAuth
import ui.toolWindow.util.UIProperties
import javax.swing.JComboBox
import javax.swing.JComponent
import javax.swing.JTabbedPane
import javax.swing.JTextField
import javax.swing.border.EmptyBorder

class RequestPane : JComponent() {
    private val objState = RequestPanePersistenceService.instance.objState
    private var requestTabbedPane: JTabbedPane = JTabbedPane()
    private val paramsRequestPane = ParamsRequestPane().createPanel()
    private val headersRequestPane = HeadersRequestPane().createPanel()
    private val bodyRequestPane = BodyRequestPane().createPanel()
    private val authRequestPane = MainAuth().createPanel()
    private val tabbedPanes = TabbedPaneList()
    private val urlTextField = JTextField(
        objState.url,
        objState.url.allText(),
        UIProperties.getProperty("columnsNumberURL").toInt()
    )
    private val methodsComboBox = JComboBox(objState.methods)

    fun createRequestPane(): DialogPanel = panel() {
        with(requestTabbedPane) {
            addTab(tabbedPanes.listOfPanes[0], paramsRequestPane)
            addTab(tabbedPanes.listOfPanes[1], headersRequestPane)
            addTab(tabbedPanes.listOfPanes[2], bodyRequestPane)
            addTab(tabbedPanes.listOfPanes[3], authRequestPane)
        }
        row {
            cell {
                methodsComboBox()
                urlTextField()
                button("SEND") {
                    service<HttpRequestSenderService<String>>().send()
                }
            }
        }
        row {
            requestTabbedPane().constraints(CCFlags.grow)
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
