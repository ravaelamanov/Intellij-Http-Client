package ui.toolWindow.request

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.panel
import services.persistence.RequestPanePersistenceService
import java.util.Vector
import javax.swing.JComponent
import javax.swing.JTable
import javax.swing.table.DefaultTableModel

class HeadersRequestPane : JComponent(), RequestTabbedPane {
    override fun createPanel(): DialogPanel = panel() {
        val columnsHeader: Vector<String> = Vector()
        columnsHeader.add("KEY")
        columnsHeader.add("VALUE")
        val data = RequestPanePersistenceService.instance.objState.headersKeyValueTable
        val defaultTableModel = DefaultTableModel(data, columnsHeader)
        val jTable = JTable(defaultTableModel)
        row {
            right {
                button("Add") {
                    defaultTableModel.addRow(Vector<String>(2))
                }
                button("Remove") {
                    if (jTable.selectedRow != -1) {
                        defaultTableModel.removeRow(jTable.selectedRow)
                    } else {
                        defaultTableModel.removeRow(defaultTableModel.rowCount - 1)
                    }
                }
            }
            row {
                scrollPane(jTable).constraints(pushX)
            }
        }
    }
}
