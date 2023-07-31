package dmitriy.losev.network.plugins

import dmitriy.losev.network.TIMEOUT
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.plugins.HttpTimeout

fun HttpClientConfig<OkHttpConfig>.configureTimeout() {
    install(HttpTimeout) {
        requestTimeoutMillis = TIMEOUT
    }
}

