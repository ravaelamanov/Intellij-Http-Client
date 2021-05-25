package ui.toolWindow.request

import com.intellij.openapi.ui.DialogPanel

interface RequestTabbedPane {
    fun createPanel(): DialogPanel
}
