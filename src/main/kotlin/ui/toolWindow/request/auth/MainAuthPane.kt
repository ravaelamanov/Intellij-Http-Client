package ui.toolWindow.request.auth

import ui.toolWindow.request.authentication.BasicPane
import javax.swing.JComponent
import javax.swing.JSplitPane

class MainAuthPane : JComponent() {
    var authTypes = AuthChoices()
    var leftPane = authTypes.createPanel()
    var noAuth = NoAuthPane().createPanel()
    var basic = BasicPane().createPanel()
    var bearer = BearerPane().createPanel()
    var splitPane = JSplitPane()

    init {
        splitPane.orientation = JSplitPane.HORIZONTAL_SPLIT
        splitPane.resizeWeight = DIVIDE_PROPORTION
        splitPane.leftComponent = leftPane
        splitPane.rightComponent = noAuth
    }

    companion object {
        private const val DIVIDE_PROPORTION = 0.5
    }

    fun createPanel(): JSplitPane = splitPane
}
