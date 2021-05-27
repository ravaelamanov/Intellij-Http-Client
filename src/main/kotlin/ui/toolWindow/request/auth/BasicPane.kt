package ui.toolWindow.request.authentication

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.CCFlags
import com.intellij.ui.layout.panel
import services.persistence.BasicAuthPersistenceService
import ui.toolWindow.request.RequestTabbedPane
import javax.swing.JCheckBox
import javax.swing.JComponent
import javax.swing.JPasswordField
import javax.swing.JTextField
import javax.swing.UIManager
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

class BasicPane : RequestTabbedPane, JComponent() {
    val usernameField = JTextField(BasicAuthPersistenceService.instance.objState.userName)
    val passwordField = JPasswordField(BasicAuthPersistenceService.instance.objState.password)
    var checkbox = JCheckBox("Show password")
    override fun createPanel(): DialogPanel = panel() {
        with(usernameField) {
            document.addDocumentListener(object : DocumentListener {
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
                    BasicAuthPersistenceService.instance.objState.userName = usernameField.text
                }
            })
        }
        with(passwordField) {
            document.addDocumentListener(object : DocumentListener {
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
                    BasicAuthPersistenceService.instance.objState.password = passwordField.text
                }
            })
        }
        checkbox.addActionListener { ae ->
            val c = ae.source as JCheckBox
            passwordField.echoChar = if (c.isSelected) '\u0000' else UIManager.get("PasswordField.echoChar") as Char
        }
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
