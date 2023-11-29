package dmitriy.losev.vk.data.di

import dmitriy.losev.vk.data.network.VkNetwork
import dmitriy.losev.vk.data.network.VkNetworkImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val vkNetworkModule = module {

    factoryOf(::VkNetworkImpl) {
        bind<VkNetwork>()
    }
}