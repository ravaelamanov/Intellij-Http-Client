package ui.toolWindow.util

import java.io.InputStream
import java.util.Properties

object ResourceLoader {
    inline fun <reified T> inputStreamForPath(path: String): InputStream {
        return T::class.java.classLoader.getResourceAsStream(path) ?: throw NullPointerException()
    }

    inline fun <reified T, R> load(path: String, modify: (inputStream: InputStream) -> R): R {
        val inputStream = inputStreamForPath<T>(path)
        inputStream.use { return modify(it) }
    }

    inline fun <reified T> load(path: String): InputStream = inputStreamForPath<T>(path)

    inline fun <reified T> loadProperties(path: String) = load<T, Properties>(path) {
        val properties = Properties()
        properties.load(it)
        return@load properties
    }
}
