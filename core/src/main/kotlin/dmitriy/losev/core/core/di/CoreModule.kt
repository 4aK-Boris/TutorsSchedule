package dmitriy.losev.core.core.di

import dmitriy.losev.core.data.di.coreRepositoryModule
import dmitriy.losev.core.domain.di.coreUseCaseModule
import dmitriy.losev.exception.ErrorHandler
import org.koin.dsl.module

val coreModule = module {

    single {
        ErrorHandler()
    }

    includes(
        coreUseCaseModule,
        dataStoreModule,
        coreRepositoryModule
    )
}