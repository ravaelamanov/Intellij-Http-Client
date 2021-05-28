package ui.toolWindow.request.auth

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.CCFlags
import com.intellij.ui.layout.panel
import services.extensions.allText
import services.persistence.BasicAuthPersistenceService
import ui.toolWindow.request.RequestTabbedPane
import ui.toolWindow.util.UIProperties
import javax.swing.JCheckBox
import javax.swing.JComponent
import javax.swing.JPasswordField
import javax.swing.JTextField
import javax.swing.UIManager

class BasicPane : RequestTabbedPane, JComponent() {
    private val objState = BasicAuthPersistenceService.instance.objState
    private val usernameField = JTextField(
        objState.userName,
        objState.userName.allText(),
        UIProperties.getProperty("columnsNumberUsername").toInt()
    )
    private val passwordField = JPasswordField(
        objState.password,
        objState.password.allText(),
        UIProperties.getProperty("columnsNumberPassword").toInt()
    )
    private var checkbox = JCheckBox("Show password")

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
