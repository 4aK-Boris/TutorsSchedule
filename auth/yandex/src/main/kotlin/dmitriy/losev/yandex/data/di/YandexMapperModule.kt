package dmitriy.losev.yandex.data.di

import dmitriy.losev.yandex.data.mappers.TokenMapper
import dmitriy.losev.yandex.data.mappers.YandexUserDataMapper
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val yandexMapperModule = module {

    factoryOf(::YandexUserDataMapper)

    factoryOf(::TokenMapper)
}