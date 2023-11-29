package dmitriy.losev.vk.domain.di

import dmitriy.losev.vk.domain.usecases.VkAuthenticationUseCases
import dmitriy.losev.vk.domain.usecases.VkTokenUseCase
import dmitriy.losev.vk.domain.usecases.VkUpdateInformationUseCase
import dmitriy.losev.vk.domain.usecases.VkUserDataUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val vkUseCaseModule = module {

    factoryOf(::VkAuthenticationUseCases)

    factoryOf(::VkTokenUseCase)

    factoryOf(::VkUpdateInformationUseCase)

    factoryOf(::VkUserDataUseCase)
}