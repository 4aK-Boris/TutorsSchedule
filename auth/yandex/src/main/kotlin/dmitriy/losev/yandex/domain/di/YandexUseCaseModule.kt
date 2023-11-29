package dmitriy.losev.yandex.domain.di

import dmitriy.losev.yandex.domain.usecases.YandexAuthenticationUseCases
import dmitriy.losev.yandex.domain.usecases.YandexDataUseCase
import dmitriy.losev.yandex.domain.usecases.YandexTokenUseCase
import dmitriy.losev.yandex.domain.usecases.YandexUpdateInformationUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val yandexUseCaseModule = module {

    factoryOf(::YandexAuthenticationUseCases)

    factoryOf(::YandexDataUseCase)

    factoryOf(::YandexTokenUseCase)

    factoryOf(::YandexUpdateInformationUseCase)
}