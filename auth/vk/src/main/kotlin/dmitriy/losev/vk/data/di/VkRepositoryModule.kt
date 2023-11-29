package dmitriy.losev.vk.data.di

import dmitriy.losev.vk.data.repositories.VkDataRepositoryImpl
import dmitriy.losev.vk.data.repositories.VkTokenRepositoryImpl
import dmitriy.losev.vk.domain.repositories.VkDataRepository
import dmitriy.losev.vk.domain.repositories.VkTokenRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val vkDataRepositoryModule = module {

    factoryOf(::VkDataRepositoryImpl) {
        bind<VkDataRepository>()
    }

    factoryOf(::VkTokenRepositoryImpl) {
        bind<VkTokenRepository>()
    }
}