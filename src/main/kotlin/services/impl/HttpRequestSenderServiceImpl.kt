package services.impl

import services.HttpRequestSenderService
import services.extensions.addParameter
import services.persistence.RequestPanePersistenceService
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class HttpRequestSenderServiceImpl : HttpRequestSenderService<String> {

    private val requestPaneState = RequestPanePersistenceService.instance.objState

    override fun send(): HttpResponse<String> {
        val httpClient = HttpClient.newBuilder().build()
        val httpRequest = buildRequestFromUI()
        val httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString())
        persistResponse(httpResponse)
        return httpResponse
    }

    private fun buildRequestFromUI(): HttpRequest {
        var uri = URI(requestPaneState.url.trim())
        requestPaneState.parametersKeyValueTable.forEach { uri = uri.addParameter(it[0] ?: "", it[1] ?: "") }
        val filteredHeaders = requestPaneState.headersKeyValueTable.filterNot { it[0] == null || it [0] == "" }
        val requestBuilder = HttpRequest.newBuilder()
        if (filteredHeaders.isNotEmpty()) {
            requestBuilder.headers(*filteredHeaders.flatMap { it.toList() }.toTypedArray())
        }
        return requestBuilder
            .method(
                requestPaneState.method.toUpperCase(),
                HttpRequest.BodyPublishers.ofString(requestPaneState.body)
            )
            .uri(uri)
            .build()
    }

    private fun persistResponse(httpResponse: HttpResponse<String>) {
        print(httpResponse)
        TODO()
    }
}
