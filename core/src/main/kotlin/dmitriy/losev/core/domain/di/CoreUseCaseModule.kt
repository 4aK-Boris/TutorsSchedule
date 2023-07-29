package dmitriy.losev.core.domain.di

import dmitriy.losev.core.domain.usecases.DataStoreUseCases
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val coreUseCaseModule = module {

    factoryOf(::DataStoreUseCases)
}