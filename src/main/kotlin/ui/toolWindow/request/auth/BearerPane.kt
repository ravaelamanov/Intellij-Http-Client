package ui.toolWindow.request.auth

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.CCFlags
import com.intellij.ui.layout.panel
import ui.toolWindow.request.RequestTabbedPane
import javax.swing.JComponent
import javax.swing.JTextField

class BearerPane : RequestTabbedPane, JComponent() {
    override fun createPanel(): DialogPanel = panel() {
        val token = JTextField()
        row("Token:") { token().constraints(CCFlags.growY) }
    }
}
