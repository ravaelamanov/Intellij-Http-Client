package ui.toolWindow

class TabbedPaneList {
    val listOfPanes: List<String> = arrayListOf("Params", "Headers", "Body", "Auth")
    val methodsList: List<String> = arrayListOf(
        "GET", "HEAD", "POST", "PUT",
        "DELETE", "OPTIONS", "TRACE", "PATCH"
    )
}
