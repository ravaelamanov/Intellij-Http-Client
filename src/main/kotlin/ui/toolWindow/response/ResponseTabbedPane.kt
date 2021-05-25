package ui.toolWindow.response

import com.intellij.openapi.ui.DialogPanel

interface ResponseTabbedPane {

    fun createPanel(): DialogPanel
}
