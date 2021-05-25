package services.extensions

import java.net.URI

fun URI.addParameter(name: String, value: String): URI {
    val stringToAdd = "$name=$value"
    return URI(
        this.scheme,
        this.authority,
        this.path,
        this.query?.plus("&$stringToAdd") ?: stringToAdd,
        this.fragment
    )
}
