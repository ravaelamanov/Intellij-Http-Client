package ui.toolWindow.util

import java.util.Properties

object UIProperties {
    private const val path = "uiParameters.properties"
    private val properties: Properties by lazy {
        ResourceLoader.loadProperties<UIProperties>(path)
    }

    fun getProperty(key: String): String = properties.getProperty(key)
}
