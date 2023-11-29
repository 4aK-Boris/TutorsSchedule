package dmitriy.losev.profile.data.di

import dmitriy.losev.profile.data.repositories.EmailRepositoryImpl
import dmitriy.losev.profile.data.repositories.PasswordRepositoryImpl
import dmitriy.losev.profile.domain.repositories.EmailRepository
import dmitriy.losev.profile.domain.repositories.PasswordRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val profileRepositoryModule = module {

    factoryOf(::EmailRepositoryImpl) {
        bind<EmailRepository>()
    }

    factoryOf(::PasswordRepositoryImpl) {
        bind<PasswordRepository>()
    }
}