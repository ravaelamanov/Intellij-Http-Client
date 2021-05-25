package ui.toolWindow

import ui.toolWindow.request.RequestPane
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JSplitPane

class CreatePane : JComponent() {
    var requestPane = RequestPane()
    var splitPane = JSplitPane(
        JSplitPane.VERTICAL_SPLIT, requestPane.createRequestPane(), JPanel()
    )
    init {
        splitPane.resizeWeight = DIVIDE_PROPORTION
    }
    companion object {
        private const val DIVIDE_PROPORTION = 0.5
    }
}
