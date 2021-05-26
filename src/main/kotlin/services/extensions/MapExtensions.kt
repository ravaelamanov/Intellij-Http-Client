package services.extensions

inline fun <reified T> Map<T, List<T>>.toArray(): Array<T> {
    return flatMap { it ->
        val key = it.toPair().first
        val value = it.toPair().second
        val result: MutableList<T> = mutableListOf()
        value.forEach { result.addAll(listOf(key, it)) }
        result
    }.toTypedArray()
}
