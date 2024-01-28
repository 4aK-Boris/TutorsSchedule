package dmitriy.losev.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readBytes
import io.ktor.http.isSuccess

class KtorFileClient(private val client: HttpClient) {

    private suspend fun call(request: suspend () -> HttpResponse): ByteArray? = try {
        val response = request()
        if (!response.status.isSuccess()) {
            null
        } else {
            response.readBytes()
        }
    } catch (e: Exception) {
        null
    }

    private suspend fun getRequest(url: String): HttpResponse = client.get(urlString = url)

    suspend fun getFile(url: String): ByteArray? = call {
        getRequest(url = url)
    }
}