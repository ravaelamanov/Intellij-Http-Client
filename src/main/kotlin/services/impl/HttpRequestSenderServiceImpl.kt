package services.impl

import services.HttpRequestSenderService
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class HttpRequestSenderServiceImpl : HttpRequestSenderService<String> {

    override fun send(): HttpResponse<String> {
        val httpClient = HttpClient.newBuilder().build()
        val httpRequest = buildRequestFromUI()
        val httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString())
        persistResponse(httpResponse)
        return httpResponse
    }

    private fun buildRequestFromUI(): HttpRequest {
        TODO()
    }

    private fun persistResponse(httpResponse: HttpResponse<String>) {
        print(httpResponse)
        TODO()
    }
}
