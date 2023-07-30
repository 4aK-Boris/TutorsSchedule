package dmitriy.losev.network

import dmitriy.losev.network.plugins.configureJson
import dmitriy.losev.network.plugins.configureLogging
import dmitriy.losev.network.plugins.configureTimeout
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