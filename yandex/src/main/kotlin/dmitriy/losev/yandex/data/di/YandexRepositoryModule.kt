package dmitriy.losev.yandex.data.di

import dmitriy.losev.yandex.data.repositories.YandexDataRepositoryImpl
import dmitriy.losev.yandex.domain.repositories.YandexDataRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val yandexRepositoryModule = module {

    factoryOf(::YandexDataRepositoryImpl) {
        bind<YandexDataRepository>()
    }
}