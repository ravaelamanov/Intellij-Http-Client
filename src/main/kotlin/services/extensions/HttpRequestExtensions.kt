package services.extensions

import java.net.http.HttpClient
import java.net.http.HttpRequest

fun HttpRequest.currentBuilder(): HttpRequest.Builder {
    val currentBuilder = HttpRequest.newBuilder()
    val headersArray = headers().map().toArray()
    if (headersArray.isNotEmpty()) {
        currentBuilder.headers(*headersArray)
    }
    return currentBuilder
        .method(method(), bodyPublisher()?.get() ?: HttpRequest.BodyPublishers.ofString(""))
        .expectContinue(expectContinue())
        .uri(uri())
        .version(version()?.get() ?: HttpClient.Version.HTTP_1_1)
}
