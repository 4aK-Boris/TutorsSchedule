package dmitriy.losev.network.core

import dmitriy.losev.network.core.plugins.configureJson
import dmitriy.losev.network.core.plugins.configureLogging
import dmitriy.losev.network.core.plugins.configureTimeout
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module

val networkModule = module {

    single {
        HttpClient(OkHttp) {
            configureJson()
            configureLogging()
            configureTimeout()
        }
    }

    single {
        KtorClient(client = get())
    }
}