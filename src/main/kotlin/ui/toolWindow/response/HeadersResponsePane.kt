package ui.toolWindow.response

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.panel
import services.persistence.ResponsePanePersistenceService
import java.util.Vector
import javax.swing.JComponent
import javax.swing.JTable
import javax.swing.table.DefaultTableModel

class HeadersResponsePane : ResponseTabbedPane, JComponent() {
    override fun createPanel(): DialogPanel = panel() {
        val columnsHeader: Vector<String> = Vector()
        columnsHeader.add("KEY")
        columnsHeader.add("VALUE")
        val data = ResponsePanePersistenceService.instance.objState.headersKeyValueTable
        val tableModel: DefaultTableModel = object : DefaultTableModel(data, columnsHeader) {
            override fun isCellEditable(row: Int, column: Int): Boolean {
                return false
            }
        }
        val jTable = JTable(tableModel)
        row {
            row {
                scrollPane(jTable).constraints(pushX)
            }
        }
    }
}
