package ui.toolWindow.request

import com.intellij.openapi.components.service
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.CollectionComboBoxModel
import com.intellij.ui.layout.CCFlags
import com.intellij.ui.layout.panel
import services.HttpRequestSenderService
import services.persistence.RequestPanePersistenceService
import ui.toolWindow.TabbedPaneList
import java.awt.event.ItemEvent
import javax.swing.JComboBox
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JTabbedPane
import javax.swing.JTextField
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

class RequestPane : JComponent() {
    companion object {
        private const val COLUMNS_NUMBER: Int = 20
    }

    private var requestTabbedPane: JTabbedPane = JTabbedPane()
    private val paramsRequestPane = ParamsRequestPane().createPanel()
    private val headersRequestPane = HeadersRequestPane().createPanel()
    private val bodyRequestPane = BodyRequestPane().createPanel()
    private val tabbedPanes = TabbedPaneList()
    val urlTextField = JTextField("google", COLUMNS_NUMBER)
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

    fun createRequestPane(): DialogPanel = panel(title = "REQUEST") {
        comboBoxListener()
        textFieldUpdate()
        requestTabbedPane.addTab(tabbedPanes.listOfPanes[0], paramsRequestPane)
        requestTabbedPane.addTab(tabbedPanes.listOfPanes[1], headersRequestPane)
        requestTabbedPane.addTab(tabbedPanes.listOfPanes[2], bodyRequestPane)
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
    }
}
