package dmitriy.losev.core.di

import dmitriy.losev.core.ErrorHandler
import dmitriy.losev.core.ResourcesManager
import dmitriy.losev.core.cache.cacheModule
import dmitriy.losev.core.domain.di.coreUseCaseModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreModule = module {

    includes(
        coreUseCaseModule,
        cacheModule
    )

    singleOf(::ResourcesManager)

    singleOf(::ErrorHandler)
}