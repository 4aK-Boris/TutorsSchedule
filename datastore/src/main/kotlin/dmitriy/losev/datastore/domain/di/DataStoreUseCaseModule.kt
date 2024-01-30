package dmitriy.losev.datastore.domain.di

import dmitriy.losev.datastore.domain.usecases.bool.DataStoreGetBooleanUseCase
import dmitriy.losev.datastore.domain.usecases.bool.DataStoreHasBooleanUseCase
import dmitriy.losev.datastore.domain.usecases.bool.DataStoreRemoveBooleanUseCase
import dmitriy.losev.datastore.domain.usecases.bool.DataStoreSetBooleanUseCase
import dmitriy.losev.datastore.domain.usecases.integer.DataStoreGetIntegerUseCase
import dmitriy.losev.datastore.domain.usecases.integer.DataStoreHasIntegerUseCase
import dmitriy.losev.datastore.domain.usecases.integer.DataStoreRemoveIntegerUseCase
import dmitriy.losev.datastore.domain.usecases.integer.DataStoreSetIntegerUseCase
import dmitriy.losev.datastore.domain.usecases.string.DataStoreGetStringUseCase
import dmitriy.losev.datastore.domain.usecases.string.DataStoreHasStringUseCase
import dmitriy.losev.datastore.domain.usecases.string.DataStoreRemoveStringUseCase
import dmitriy.losev.datastore.domain.usecases.string.DataStoreSetStringUseCase
import dmitriy.losev.datastore.domain.usecases.string.set.DataStoreGetStringSetUseCase
import dmitriy.losev.datastore.domain.usecases.string.set.DataStoreHasStringSetUseCase
import dmitriy.losev.datastore.domain.usecases.string.set.DataStoreRemoveStringSetUseCase
import dmitriy.losev.datastore.domain.usecases.string.set.DataStoreSetStringSetUseCase
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
    
    //String

    factoryOf(::DataStoreGetStringUseCase)

    factoryOf(::DataStoreHasStringUseCase)

    factoryOf(::DataStoreRemoveStringUseCase)

    factoryOf(::DataStoreSetStringUseCase)

    //String Set

    factoryOf(::DataStoreGetStringSetUseCase)

    factoryOf(::DataStoreHasStringSetUseCase)

    factoryOf(::DataStoreRemoveStringSetUseCase)

    factoryOf(::DataStoreSetStringSetUseCase)
}