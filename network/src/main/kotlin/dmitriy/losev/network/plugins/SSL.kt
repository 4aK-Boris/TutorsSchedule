package dmitriy.losev.network.plugins

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttpConfig
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

fun HttpClientConfig<OkHttpConfig>.configureSSL(sslSocketFactory: SSLSocketFactory, trustManager: X509TrustManager) {
    engine {
        config {
            sslSocketFactory(sslSocketFactory = sslSocketFactory, trustManager = trustManager)
        }
    }
}
