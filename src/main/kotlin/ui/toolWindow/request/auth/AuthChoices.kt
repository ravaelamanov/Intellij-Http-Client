package ui.toolWindow.request.auth

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.EnumComboBoxModel
import com.intellij.ui.layout.panel
import services.auth.AuthenticationProvider
import ui.toolWindow.request.RequestTabbedPane
import javax.swing.JComboBox
import javax.swing.JComponent

class AuthChoices : RequestTabbedPane, JComponent() {
    val comboBoxModel = EnumComboBoxModel(AuthenticationProvider.Strategies::class.java)
    val authComboBox = JComboBox(comboBoxModel)
    override fun createPanel(): DialogPanel = panel() {
        row { cell { authComboBox() } }
    }
}
