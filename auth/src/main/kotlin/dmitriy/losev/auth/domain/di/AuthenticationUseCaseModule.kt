package dmitriy.losev.auth.domain.di

import dmitriy.losev.auth.domain.usecases.AuthenticationCheckAuthUseCase
import dmitriy.losev.auth.domain.usecases.AuthenticationCreateDirUseCase
import dmitriy.losev.auth.domain.usecases.AuthenticationEmailAuthUseCase
import dmitriy.losev.auth.domain.usecases.AuthenticationEmailUseCase
import dmitriy.losev.auth.domain.usecases.AuthenticationGoogleUseCase
import dmitriy.losev.auth.domain.usecases.AuthenticationListenerUseCase
import dmitriy.losev.auth.domain.usecases.AuthenticationNavigationUseCases
import dmitriy.losev.auth.domain.usecases.AuthenticationPasswordResetUseCase
import dmitriy.losev.auth.domain.usecases.AuthenticationPasswordUseCase
import dmitriy.losev.auth.domain.usecases.AuthenticationRegistrationUseCase
import dmitriy.losev.auth.domain.usecases.AuthenticationStartUseCase
import dmitriy.losev.auth.domain.usecases.AuthenticationUpdateInformationUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val authenticationUseCaseModule = module {

    factoryOf(::AuthenticationEmailUseCase)

    factoryOf(::AuthenticationGoogleUseCase)

    factoryOf(::AuthenticationListenerUseCase)

    factoryOf(::AuthenticationNavigationUseCases)

    factoryOf(::AuthenticationPasswordResetUseCase)

    factoryOf(::AuthenticationPasswordUseCase)

    factoryOf(::AuthenticationRegistrationUseCase)

    factoryOf(::AuthenticationUpdateInformationUseCase)

    factoryOf(::AuthenticationEmailAuthUseCase)

    factoryOf(::AuthenticationCheckAuthUseCase)

    factoryOf(::AuthenticationCreateDirUseCase)

    factoryOf(::AuthenticationStartUseCase)
}