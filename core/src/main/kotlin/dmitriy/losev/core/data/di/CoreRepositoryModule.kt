package dmitriy.losev.core.data.di

import dmitriy.losev.core.data.repositories.DataStoreRepositoryImpl
import dmitriy.losev.core.domain.repositories.DataStoreRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val coreRepositoryModule = module {

    factoryOf(::DataStoreRepositoryImpl) {
        bind<DataStoreRepository>()
    }
}