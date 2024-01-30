package dmitriy.losev.core.di

import dmitriy.losev.core.ErrorHandler
import dmitriy.losev.core.ResourcesManager
import dmitriy.losev.core.cache.cacheModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreModule = module {

    includes(
        cacheModule
    )

    singleOf(::ResourcesManager)

    singleOf(::ErrorHandler)
}