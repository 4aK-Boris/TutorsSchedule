package dmitriy.losev.network.plugins

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE

fun HttpClientConfig<OkHttpConfig>.configureLogging() {
    install(Logging) {
        logger = Logger.SIMPLE
        level = LogLevel.ALL
    }
}
