package dmitriy.losev.core.core.di

import dmitriy.losev.core.data.di.coreRepositoryModule
import dmitriy.losev.core.domain.di.coreUseCaseModule
import org.koin.dsl.module

val coreModule = module {

    includes(
        coreUseCaseModule,
        dataStoreModule,
        coreRepositoryModule
    )
}