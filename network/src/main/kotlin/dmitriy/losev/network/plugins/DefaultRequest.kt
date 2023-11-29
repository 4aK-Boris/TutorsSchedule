package dmitriy.losev.network.plugins

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType

private val defaultHeaders: MutableMap<String, String> = mutableMapOf()

fun HttpClientConfig<*>.configureDefaultRequest() {
    defaultRequest {
        headers.clear()
        defaultHeaders.forEach {
            headers.append(it.key, it.value)
        }
        contentType(ContentType.Application.Json)
    }
}
