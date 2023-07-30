package dmitriy.losev.yandex.domain.di

import dmitriy.losev.yandex.domain.usecases.YandexAuthenticationUseCases
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val yandexUseCaseModule = module {

    factoryOf(::YandexAuthenticationUseCases)
}