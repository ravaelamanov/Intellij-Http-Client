package ui.toolWindow.request.authentication

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.CCFlags
import com.intellij.ui.layout.panel
import ui.toolWindow.request.RequestTabbedPane
import javax.swing.JCheckBox
import javax.swing.JComponent
import javax.swing.JPasswordField
import javax.swing.JTextField

class BasicPane : RequestTabbedPane, JComponent() {
    val usernameField = JTextField()
    val passwordField = JPasswordField()
    var checkbox = JCheckBox("Show password")
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
