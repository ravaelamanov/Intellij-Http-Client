package ui.toolWindow.request

import com.intellij.openapi.components.service
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.CollectionComboBoxModel
import com.intellij.ui.layout.panel
import services.HttpRequestSenderService
import services.persistence.RequestPanePersistenceService
import ui.toolWindow.TabbedPaneList
import javax.swing.JComboBox
import javax.swing.JComponent
import javax.swing.JTextField


class RequestPane : JComponent() {
    companion object {
        private const val COLUMNS_NUMBER: Int = 20
    }

    private val tabbedPanes = TabbedPaneList()
    val comboBoxModel = CollectionComboBoxModel(tabbedPanes.methodsList)
    val methodsComboBox = JComboBox(comboBoxModel)
    val urlTextField = JTextField("google", COLUMNS_NUMBER)
    fun createRequestPane(): DialogPanel = panel(title = "REQUEST") {
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