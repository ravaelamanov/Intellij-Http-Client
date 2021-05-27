package ui.toolWindow.request.auth

import com.intellij.openapi.ui.DialogPanel
import com.sun.java.accessibility.util.AWTEventMonitor.addItemListener
import services.auth.AuthenticationProvider
import ui.toolWindow.request.authentication.BasicPane
import java.awt.event.ItemEvent
import javax.swing.JComponent
import javax.swing.JSplitPane

class MainAuth : JComponent() {
    var authTypes = AuthChoicesPane()
    var leftPane = authTypes.createPanel()
    var noAuth = NoAuthPane().createPanel()
    var basic = BasicPane().createPanel()
    var bearer = BearerPane().createPanel()
    var splitPane = JSplitPane()

    private fun changeSplitPane(panel: DialogPanel) {
        splitPane.remove(splitPane.rightComponent)
        splitPane.repaint()
        splitPane.revalidate()
        splitPane.add(panel)
        splitPane.repaint()
        splitPane.revalidate()
    }

    init {
        splitPane.orientation = JSplitPane.HORIZONTAL_SPLIT
        splitPane.resizeWeight = DIVIDE_PROPORTION
        splitPane.rightComponent = when (authTypes.comboBoxModel.selectedItem) {
            AuthenticationProvider.Strategies.No_Auth -> noAuth
            AuthenticationProvider.Strategies.Basic -> basic
            AuthenticationProvider.Strategies.Bearer -> bearer
            else -> noAuth
        }
        splitPane.leftComponent = leftPane
        authTypes.authComboBox.apply {
            addItemListener {
                if (it.stateChange == ItemEvent.SELECTED) {
                    when (authTypes.comboBoxModel.selectedItem) {
                        AuthenticationProvider.Strategies.No_Auth -> changeSplitPane(noAuth)
                        AuthenticationProvider.Strategies.Basic -> changeSplitPane(basic)
                        AuthenticationProvider.Strategies.Bearer -> changeSplitPane(bearer)
                        else -> {}
                    }
                }
            }
        }
    }

    companion object {
        private const val DIVIDE_PROPORTION = 0.5
    }

    fun createPanel(): JSplitPane = splitPane
}
