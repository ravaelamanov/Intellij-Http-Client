package ui.toolWindow

import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JSplitPane

class CreatePane : JComponent() {
    var splitPane = JSplitPane(
        JSplitPane.VERTICAL_SPLIT, JPanel(), JPanel()
    )
    init {
        splitPane.resizeWeight = DIVIDE_PROPORTION
    }
    companion object {
        private const val DIVIDE_PROPORTION = 0.5
    }
}
