package dmitriy.losev.datastore.data.di

import dmitriy.losev.datastore.data.repositories.DataStoreRepositoryImpl
import dmitriy.losev.datastore.domain.repository.DataStoreRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataStoreRepositoryModule = module {

    factoryOf(::DataStoreRepositoryImpl) {
        bind<DataStoreRepository>()
    }
}