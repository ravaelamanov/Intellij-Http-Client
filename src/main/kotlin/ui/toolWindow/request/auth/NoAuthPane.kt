package ui.toolWindow.request.auth

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.panel
import ui.toolWindow.request.RequestTabbedPane
import ui.toolWindow.util.UIProperties
import javax.swing.JComponent
import javax.swing.border.EmptyBorder

class NoAuthPane : RequestTabbedPane, JComponent() {
    override fun createPanel(): DialogPanel = panel() {
        noteRow("No authorization will be provided.")
    }.apply {
        withBorder(
            EmptyBorder(
                UIProperties.getProperty("paddingTop").toInt(),
                UIProperties.getProperty("paddingLeft").toInt(),
                UIProperties.getProperty("paddingBottom").toInt(),
                UIProperties.getProperty("paddingRight").toInt()
            )
        )
    }
}
