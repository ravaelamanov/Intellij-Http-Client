package services.extensions

import java.net.http.HttpRequest

fun HttpRequest.currentBuilder(): HttpRequest.Builder {
    val currentBuilder = HttpRequest.newBuilder()
    return currentBuilder
        .method(method(), bodyPublisher().orElse(null))
        .expectContinue(expectContinue())
        .timeout(timeout().orElse(null))
        .uri(uri())
        .version(version().orElse(null))
        .headers(
            *headers().map().toArray()
        )
}
