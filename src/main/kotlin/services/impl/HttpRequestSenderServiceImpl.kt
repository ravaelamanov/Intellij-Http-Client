package services.impl

import services.HttpRequestSenderService
import services.extensions.addParameter
import services.persistence.RequestPanePersistenceService
import services.persistence.ResponsePanePersistenceService
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.Vector

class HttpRequestSenderServiceImpl : HttpRequestSenderService<String> {

    private val requestPaneState = RequestPanePersistenceService.instance.objState
    private val responsePaneState = ResponsePanePersistenceService.instance.objState
    private var authenticationProvider = requestPaneState.auth.provider

    override fun send(): HttpResponse<String> {
        val httpClient = HttpClient.newBuilder().build()
        val httpRequest = buildRequestFromUI()
        val authenticatedHttpRequest = authenticationProvider.authenticate(httpRequest)
        val httpResponse = httpClient.send(authenticatedHttpRequest, HttpResponse.BodyHandlers.ofString())
        persistResponse(httpResponse)
        return httpResponse
    }

    private fun buildRequestFromUI(): HttpRequest {
        authenticationProvider = requestPaneState.auth.provider
        var uri = URI(requestPaneState.url.trim())
        requestPaneState.parametersKeyValueTable.forEach { uri = uri.addParameter(it[0] ?: "", it[1] ?: "") }
        val filteredHeaders = requestPaneState.headersKeyValueTable.filterNot { it[0] == null || it[0] == "" }
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
        responsePaneState.body = httpResponse.body()
        responsePaneState.headersKeyValueTable.removeAllElements()
        httpResponse.headers().map().forEach { key, values ->
            values.forEach { value ->
                val vectorToAdd = Vector<String>(2)
                vectorToAdd.add(key)
                vectorToAdd.add(value)
                responsePaneState.headersKeyValueTable.add(vectorToAdd)
            }
        }
    }
}
