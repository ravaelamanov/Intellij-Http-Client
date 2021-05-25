package services

import java.net.http.HttpResponse

interface HttpRequestSenderService<T> {
    fun send(): HttpResponse<T>
}
