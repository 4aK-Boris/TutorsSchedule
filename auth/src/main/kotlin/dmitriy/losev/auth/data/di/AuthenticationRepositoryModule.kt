package dmitriy.losev.auth.data.di

import dmitriy.losev.auth.data.repository.EmailValidationRepositoryImpl
import dmitriy.losev.auth.data.repository.PasswordValidationRepositoryImpl
import dmitriy.losev.auth.domain.repository.EmailValidationRepository
import dmitriy.losev.auth.domain.repository.PasswordValidationRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val authenticationRepositoryModule = module {

    factoryOf(::PasswordValidationRepositoryImpl) {
        bind<PasswordValidationRepository>()
    }

    factoryOf(::EmailValidationRepositoryImpl) {
        bind<EmailValidationRepository>()
    }
}