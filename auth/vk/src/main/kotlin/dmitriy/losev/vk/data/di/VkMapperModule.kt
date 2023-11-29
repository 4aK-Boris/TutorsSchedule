package dmitriy.losev.vk.data.di

import dmitriy.losev.vk.data.mappers.VkUserDataMapper
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val vkMapperModule = module {

    factoryOf(::VkUserDataMapper)
}