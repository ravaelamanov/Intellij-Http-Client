package ui.toolWindow.request

import com.intellij.openapi.components.service
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.CollectionComboBoxModel
import com.intellij.ui.layout.panel
import services.HttpRequestSenderService
import services.persistence.RequestPanePersistenceService
import ui.toolWindow.TabbedPaneList
import java.awt.event.ItemEvent
import javax.swing.JComboBox
import javax.swing.JComponent
import javax.swing.JTabbedPane
import javax.swing.JTextField
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener


class RequestPane : JComponent() {
    companion object {
        private const val COLUMNS_NUMBER: Int = 20
    }
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
        row {
            cell {
                methodsComboBox()
                urlTextField()
                button("SEND") {

                }
            }
        }
    }
}