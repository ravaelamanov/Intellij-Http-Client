package services.persistence.converters

import com.intellij.util.xmlb.Converter
import java.util.Vector

class VectorConverter : Converter<Vector<Vector<String>>>() {
    private companion object {
        const val separator = "::"
    }

    override fun toString(value: Vector<Vector<String>>): String {
        return value.flatten().joinToString(separator)
    }

    override fun fromString(value: String): Vector<Vector<String>> {
        val keyValueTable = Vector<Vector<String>>(1)
        value.split(separator).windowed(2, 2) { it: List<String?> ->
            val mutableList = it.toMutableList()
            mutableList.replaceAll {
                when (it) {
                    "null" -> null
                    else -> it
                }
            }
            mutableList
        }.forEach {
            val vectorToAdd = Vector<String>(2)
            it.forEach { vectorToAdd.add(it) }
            keyValueTable.add(vectorToAdd)
        }
        return keyValueTable
    }
}
