package dmitriy.losev.auth.data.di

import dmitriy.losev.auth.data.network.AuthenticationNetwork
import dmitriy.losev.auth.data.network.AuthenticationNetworkImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val authenticationNetworkModule = module {

    factoryOf(::AuthenticationNetworkImpl) {
        bind<AuthenticationNetwork>()
    }
}