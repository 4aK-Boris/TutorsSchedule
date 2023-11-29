package dmitriy.losev.datastore.domain.di

import dmitriy.losev.datastore.domain.usecases.DataStoreGetIntegerUseCase
import dmitriy.losev.datastore.domain.usecases.DataStoreHasIntegerUseCase
import dmitriy.losev.datastore.domain.usecases.DataStoreRemoveIntegerUseCase
import dmitriy.losev.datastore.domain.usecases.DataStoreSetIntegerUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataStoreUseCaseModule = module {

    factoryOf(::DataStoreGetIntegerUseCase)

    factoryOf(::DataStoreHasIntegerUseCase)

    factoryOf(::DataStoreRemoveIntegerUseCase)

    factoryOf(::DataStoreSetIntegerUseCase)
}