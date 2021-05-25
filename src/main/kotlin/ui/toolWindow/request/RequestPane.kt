package ui.toolWindow.request

import com.intellij.openapi.components.service
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.CollectionComboBoxModel
import com.intellij.ui.layout.panel
import ui.toolWindow.TabbedPaneList
import javax.swing.JComboBox
import javax.swing.JComponent


class RequestPane: JComponent() {
    private val tabbedPanes = TabbedPaneList()
    val comboBoxModel = CollectionComboBoxModel(tabbedPanes.methodsList)
    val methodsComboBox = JComboBox(comboBoxModel)
    fun createRequestPane(): DialogPanel = panel(title = "REQUEST") {
        row {
            cell {
                methodsComboBox()
            }
        }
    }
}