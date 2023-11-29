package dmitriy.losev.vk.core

import dmitriy.losev.vk.data.di.vkMapperModule
import dmitriy.losev.vk.data.di.vkNetworkModule
import dmitriy.losev.vk.data.di.vkDataRepositoryModule
import dmitriy.losev.vk.domain.di.vkUseCaseModule
import dmitriy.losev.vk.presentation.di.vkViewModelModule
import org.koin.dsl.module

val vkModule = module {

    includes(
        vkMapperModule,
        vkDataRepositoryModule,
        vkNetworkModule,
        vkUseCaseModule,
        vkViewModelModule
    )
}