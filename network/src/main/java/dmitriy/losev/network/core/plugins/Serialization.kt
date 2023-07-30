package dmitriy.losev.network.core.plugins

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

fun HttpClientConfig<OkHttpConfig>.configureJson() {
    install(ContentNegotiation) {
        json()
    }
}

