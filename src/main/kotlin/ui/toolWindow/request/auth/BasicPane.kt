package ui.toolWindow.request.authentication

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.CCFlags
import com.intellij.ui.layout.panel
import services.extensions.allText
import services.persistence.BasicAuthPersistenceService
import ui.toolWindow.request.RequestTabbedPane
import javax.swing.JCheckBox
import javax.swing.JComponent
import javax.swing.JPasswordField
import javax.swing.JTextField
import javax.swing.UIManager

class BasicPane : RequestTabbedPane, JComponent() {
    val objState = BasicAuthPersistenceService.instance.objState
    val usernameField = JTextField(objState.userName, objState.userName.allText(), 20)
    val passwordField = JPasswordField(objState.password, objState.password.allText(), 20)
    var checkbox = JCheckBox("Show password")

    init {
        checkbox.addActionListener { ae ->
            val c = ae.source as JCheckBox
            passwordField.echoChar = if (c.isSelected) '\u0000' else UIManager.get("PasswordField.echoChar") as Char
        }
    }

    override fun createPanel(): DialogPanel = panel() {

        row("Username:") {
            usernameField().constraints(CCFlags.growY)
        }
        row("Password:") {
            passwordField().constraints(CCFlags.growY)
        }
        row {
            checkbox().constraints(CCFlags.growY)
        }
    }
}
