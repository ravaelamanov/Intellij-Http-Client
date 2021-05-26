package ui.toolWindow.request.auth

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.panel
import ui.toolWindow.request.RequestTabbedPane
import javax.swing.JComponent

class NoAuthPane : RequestTabbedPane, JComponent() {
    override fun createPanel(): DialogPanel = panel() {
        noteRow("No authorization will be provided.")
    }
}
