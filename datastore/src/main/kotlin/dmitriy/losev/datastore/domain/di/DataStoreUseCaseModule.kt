package dmitriy.losev.datastore.domain.di

import dmitriy.losev.datastore.domain.usecases.bool.DataStoreGetBooleanUseCase
import dmitriy.losev.datastore.domain.usecases.bool.DataStoreHasBooleanUseCase
import dmitriy.losev.datastore.domain.usecases.bool.DataStoreRemoveBooleanUseCase
import dmitriy.losev.datastore.domain.usecases.bool.DataStoreSetBooleanUseCase
import dmitriy.losev.datastore.domain.usecases.integer.DataStoreGetIntegerUseCase
import dmitriy.losev.datastore.domain.usecases.integer.DataStoreHasIntegerUseCase
import dmitriy.losev.datastore.domain.usecases.integer.DataStoreRemoveIntegerUseCase
import dmitriy.losev.datastore.domain.usecases.integer.DataStoreSetIntegerUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataStoreUseCaseModule = module {

    //Integer

    factoryOf(::DataStoreGetIntegerUseCase)

    factoryOf(::DataStoreHasIntegerUseCase)

    factoryOf(::DataStoreRemoveIntegerUseCase)

    factoryOf(::DataStoreSetIntegerUseCase)

    //Boolean

    factoryOf(::DataStoreGetBooleanUseCase)

    factoryOf(::DataStoreHasBooleanUseCase)

    factoryOf(::DataStoreRemoveBooleanUseCase)

    factoryOf(::DataStoreSetBooleanUseCase)
}