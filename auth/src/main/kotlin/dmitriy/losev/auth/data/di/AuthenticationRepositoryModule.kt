package dmitriy.losev.auth.data.di

import dmitriy.losev.auth.data.repository.AuthenticationValidateRepositoryImpl
import dmitriy.losev.auth.domain.repository.AuthenticationValidateRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val authenticationRepositoryModule = module {

    factoryOf(::AuthenticationValidateRepositoryImpl) {
        bind<AuthenticationValidateRepository>()
    }
}