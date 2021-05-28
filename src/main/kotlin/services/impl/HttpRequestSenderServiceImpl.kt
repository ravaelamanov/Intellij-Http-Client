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

    override fun send(): HttpResponse<String> {
        val httpClient = HttpClient.newBuilder().build()
        val httpRequest = buildRequestFromUI()
        val authenticationProvider = (requestPaneState.auths.selectedItem as AuthenticationProvider.Strategy).provider
        val authenticatedHttpRequest = authenticationProvider.authenticate(httpRequest)
        val httpResponse = httpClient.send(authenticatedHttpRequest, HttpResponse.BodyHandlers.ofString())
        persistResponse(httpResponse)
        return httpResponse
    }

    private fun buildRequestFromUI(): HttpRequest {
        val method = requestPaneState.methods.selectedItem as String
        val body = requestPaneState.body.allText()
        val url = requestPaneState.url.allText()
        var uri = URI(url.trim())
        requestPaneState.parametersKeyValueTable.forEach { uri = uri.addParameter(it[0] ?: "", it[1] ?: "") }
        val filteredHeaders = requestPaneState.headersKeyValueTable.filterNot { it[0] == null || it[0] == "" }
        val requestBuilder = HttpRequest.newBuilder()
        if (filteredHeaders.isNotEmpty()) {
            requestBuilder.headers(*filteredHeaders.flatMap { it.toList() }.toTypedArray())
        }
        return requestBuilder
            .method(
                method.toUpperCase(),
                HttpRequest.BodyPublishers.ofString(body)
            )
            .uri(uri)
            .build()
    }

    private fun persistResponse(httpResponse: HttpResponse<String>) {
        responsePaneState.body.setText(httpResponse.body())
        responsePaneState.headersKeyValueTable.removeAllElements()
        httpResponse.headers().map().forEach { key, values ->
            values.forEach { value ->
                val vectorToAdd = Vector<String>(2)
                vectorToAdd.add(key)
                vectorToAdd.add(value)
                responsePaneState.headersKeyValueTable.add(vectorToAdd)
            }
        }
        responsePaneState.statusCode = httpResponse.statusCode().toString()
    }
}
