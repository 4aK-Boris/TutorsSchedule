package dmitriy.losev.network.core

import dmitriy.losev.network.core.exception.BadRequest
import dmitriy.losev.network.core.exception.InternalServerError
import dmitriy.losev.network.core.exception.NoInternet
import dmitriy.losev.network.core.exception.NotFound
import dmitriy.losev.network.core.exception.UnknownNetworkException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import io.ktor.http.parameters
import io.ktor.util.network.UnresolvedAddressException

class KtorClient(private val client: HttpClient) {

    suspend inline fun <reified T : Any> call(request: () -> HttpResponse): T = try {
        val response = request()
        if (!response.status.isSuccess()) {
            handleError(response)
        }
        response.body()
    } catch (ex: UnresolvedAddressException) {
        throw NoInternet()
    } catch (e: UnknownNetworkException) {
        throw NoInternet()
    }

    suspend fun postRequest(
        url: String,
        body: Any,
        headers: Map<String, Any>,
        params: Map<String, Any>
    ): HttpResponse =
        client.post(urlString = url) {
            headers {
                headers.forEach { (key, value) ->
                    header(key = key, value = value)
                }
            }
            parameters {
                params.forEach { (key, value) ->
                    parameter(key = key, value = value)
                }
            }
            setBody(body)
        }


    suspend fun getRequest(url: String, headers: Map<String, Any>, params: Map<String, Any>): HttpResponse =
        client.get(urlString = url) {
            headers {
                headers.forEach { (key, value) ->
                    header(key = key, value = value)
                }
            }
            parameters {
                params.forEach { (key, value) ->
                    parameter(key = key, value = value)
                }
            }
        }

    suspend inline fun <T : Any, reified R : Any> post(
        url: String,
        body: T,
        headers: Map<String, Any> = emptyMap(),
        params: Map<String, Any> = emptyMap()
    ): R = call {
        postRequest(url = url, body = body, params = params, headers = headers)
    }

    suspend inline fun <reified T : Any> get(
        url: String,
        headers: Map<String, Any> = emptyMap(),
        params: Map<String, Any> = emptyMap()
    ): T = call {
        getRequest(url = url, params = params, headers = headers)
    }

    fun handleError(response: HttpResponse) {
        when (response.status) {
            HttpStatusCode.BadRequest -> throw BadRequest()
            HttpStatusCode.InternalServerError -> throw InternalServerError()
            HttpStatusCode.NotFound -> throw NotFound()
            else -> throw UnknownNetworkException()
        }
    }
}