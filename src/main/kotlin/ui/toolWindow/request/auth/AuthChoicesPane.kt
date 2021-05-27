package ui.toolWindow.request.auth

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.EnumComboBoxModel
import com.intellij.ui.layout.panel
import services.auth.AuthenticationProvider
import services.persistence.RequestPanePersistenceService
import ui.toolWindow.request.RequestTabbedPane
import java.awt.event.ItemEvent
import javax.swing.JComboBox
import javax.swing.JComponent

class AuthChoicesPane : RequestTabbedPane, JComponent() {
    val selection = RequestPanePersistenceService.instance.objState.auth
    val comboBoxModel = EnumComboBoxModel(AuthenticationProvider.Strategies::class.java)
    val authComboBox = JComboBox(comboBoxModel)
    init {
        authComboBox.addItemListener() {
            if (it.stateChange == ItemEvent.SELECTED) {
                RequestPanePersistenceService.instance.objState.auth = it.item as AuthenticationProvider.Strategies
            }
        }
        comboBoxModel.setSelectedItem(selection)
    }
    override fun createPanel(): DialogPanel = panel() {
        row { cell { authComboBox() } }
    }
}
