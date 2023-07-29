package dmitriy.losev.auth.domain.di

import dmitriy.losev.auth.domain.usecases.AuthenticationValidateUseCases
import dmitriy.losev.auth.domain.usecases.screens.DataScreenUseCases
import dmitriy.losev.auth.domain.usecases.screens.EmailLoginScreenUseCases
import dmitriy.losev.auth.domain.usecases.screens.PasswordResetScreenUseCases
import dmitriy.losev.auth.domain.usecases.screens.PasswordScreenUseCases
import dmitriy.losev.auth.domain.usecases.screens.StartScreenUseCases
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val authenticationUseCaseModule = module {

    factoryOf(::AuthenticationValidateUseCases)

    factoryOf(::DataScreenUseCases)

    factoryOf(::EmailLoginScreenUseCases)

    factoryOf(::StartScreenUseCases)

    factoryOf(::PasswordScreenUseCases)

    factoryOf(::PasswordResetScreenUseCases)
}