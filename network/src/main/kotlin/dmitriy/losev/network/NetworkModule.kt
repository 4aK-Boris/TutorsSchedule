package dmitriy.losev.network

import dmitriy.losev.network.plugins.configureDefaultRequest
import dmitriy.losev.network.plugins.configureJson
import dmitriy.losev.network.plugins.configureLogging
import dmitriy.losev.network.plugins.configureTimeout
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {

    single(qualifier = named(name = "default")) {
        HttpClient(OkHttp) {
            configureJson()
            configureLogging()
            configureTimeout()
            configureDefaultRequest()
        }
    }

    single(qualifier = named(name = "file")) {
        HttpClient(OkHttp) {
            configureLogging()
            configureTimeout()
        }
    }

    single {
        KtorClient(client = get(named(name = "default")))
    }

    single {
        KtorFileClient(client = get(named(name = "file")))
    }
}