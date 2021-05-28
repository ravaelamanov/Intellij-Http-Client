package ui.toolWindow.request

import com.intellij.openapi.components.service
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.CollectionComboBoxModel
import com.intellij.ui.layout.CCFlags
import com.intellij.ui.layout.panel
import services.HttpRequestSenderService
import services.persistence.RequestPanePersistenceService
import services.persistence.ResponsePanePersistenceService
import ui.toolWindow.TabbedPaneList
import ui.toolWindow.request.auth.MainAuth
import ui.toolWindow.response.ResponsePane
import ui.toolWindow.util.ResourceLoader
import java.awt.event.ItemEvent
import javax.swing.JComboBox
import javax.swing.JComponent
import javax.swing.JTabbedPane
import javax.swing.JTextField
import javax.swing.border.EmptyBorder
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

class RequestPane : JComponent() {
    private var requestTabbedPane: JTabbedPane = JTabbedPane()
    private val paramsRequestPane = ParamsRequestPane().createPanel()
    private val headersRequestPane = HeadersRequestPane().createPanel()
    private val bodyRequestPane = BodyRequestPane().createPanel()
    private val authRequestPane = MainAuth().createPanel()
    private val tabbedPanes = TabbedPaneList()
    private val path = "uiParameters.properties"
    private val properties = ResourceLoader.loadProperties<RequestPane>(path)
    val urlTextField = JTextField(
        RequestPanePersistenceService.instance.objState.url, properties.getProperty("columnsNumberURL").toInt()
    )
    private val savedSelection = RequestPanePersistenceService.instance.objState.method
    private val selection = if (savedSelection.isEmpty()) tabbedPanes.methodsList.first() else savedSelection
    val comboBoxModel = CollectionComboBoxModel(tabbedPanes.methodsList, selection)
    val methodsComboBox = JComboBox(comboBoxModel)
    private fun comboBoxListener() {
        methodsComboBox.addItemListener() {
            if (it.stateChange == ItemEvent.SELECTED) {
                RequestPanePersistenceService.instance.objState.method = it.item as String
            }
        }
    }

    private fun textFieldUpdate() {
        urlTextField.document.addDocumentListener(object : DocumentListener {
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
                RequestPanePersistenceService.instance.objState.url = urlTextField.text
            }
        })
    }

    fun createRequestPane(): DialogPanel = panel() {
        comboBoxListener()
        textFieldUpdate()
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
                    ResponsePane.setBodyText(ResponsePanePersistenceService.instance.objState.body)
                    ResponsePane.setStatusCode(ResponsePanePersistenceService.instance.objState.statusCode)
                }
            }
        }
        row {
            requestTabbedPane().constraints(CCFlags.grow)
        }
    }.apply {
        withBorder(
            EmptyBorder(
                properties.getProperty("paddingTop").toInt(),
                properties.getProperty("paddingLeft").toInt(),
                properties.getProperty("paddingBottom").toInt(),
                properties.getProperty("paddingRight").toInt()
            )
        )
    }
}
