package dmitriy.losev.auth.core

import dmitriy.losev.auth.data.di.authenticationRepositoryModule
import dmitriy.losev.auth.domain.di.authenticationUseCaseModule
import dmitriy.losev.auth.presentation.di.authenticationViewModelModule
import org.koin.dsl.module

val authenticationModule = module {

    includes(
        authenticationViewModelModule,
        authenticationUseCaseModule,
        authenticationRepositoryModule
    )
}