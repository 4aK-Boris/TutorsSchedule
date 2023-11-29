package dmitriy.losev.yandex.data.di

import dmitriy.losev.yandex.data.network.YandexNetwork
import dmitriy.losev.yandex.data.network.YandexNetworkImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val yandexNetworkModule = module {

    factoryOf(::YandexNetworkImpl) {
        bind<YandexNetwork>()
    }
}