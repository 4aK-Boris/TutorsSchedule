package dmitriy.losev.profile.data.di

import dmitriy.losev.profile.data.network.ProfileNetwork
import dmitriy.losev.profile.data.network.ProfileNetworkImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val profileNetworkModule = module {

    factoryOf(::ProfileNetworkImpl) {
        bind<ProfileNetwork>()
    }
}