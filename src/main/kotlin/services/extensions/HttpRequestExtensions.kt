package services.extensions

import java.net.http.HttpRequest

fun HttpRequest.currentBuilder(): HttpRequest.Builder {
    val currentBuilder = HttpRequest.newBuilder()
    val headersArray = headers().map().toArray()
    if (headersArray.isNotEmpty()) {
        currentBuilder.headers(*headersArray)
    }
    return currentBuilder
        .method(method(), bodyPublisher().orElse(HttpRequest.BodyPublishers.noBody()))
        .expectContinue(expectContinue())
        .uri(uri())
//        .version(version().orElse(HttpClient.Version.HTTP_1_1))
}
