package dmitriy.losev.network.core.plugins

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.plugins.HttpTimeout
import dmitriy.losev.network.core.TIMEOUT

fun HttpClientConfig<OkHttpConfig>.configureTimeout() {
    install(HttpTimeout) {
        requestTimeoutMillis = TIMEOUT
    }
}

