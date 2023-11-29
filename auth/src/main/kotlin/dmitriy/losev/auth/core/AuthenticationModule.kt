package dmitriy.losev.auth.core

import dmitriy.losev.auth.data.di.authenticationMapperModule
import dmitriy.losev.auth.data.di.authenticationNetworkModule
import dmitriy.losev.auth.data.di.authenticationRepositoryModule
import dmitriy.losev.auth.domain.di.authenticationUseCaseModule
import dmitriy.losev.auth.presentation.di.authenticationViewModelModule
import dmitriy.losev.vk.core.vkModule
import dmitriy.losev.yandex.core.yandexModule
import org.koin.dsl.module

val authenticationModule = module {

    includes(
        authenticationViewModelModule,
        authenticationUseCaseModule,
        authenticationRepositoryModule,
        authenticationNetworkModule,
        authenticationMapperModule,
        vkModule,
        yandexModule
    )
}