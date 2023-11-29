package dmitriy.losev.core.core.di

import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.ResourcesManager
import dmitriy.losev.core.domain.di.coreUseCaseModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreModule = module {

    includes(
        coreUseCaseModule
    )

    singleOf(::ResourcesManager)

    singleOf(::ErrorHandler)
}