package dmitriy.losev.profile.data.di

import dmitriy.losev.profile.data.repositories.AvatarRepositoryImpl
import dmitriy.losev.profile.data.repositories.EmailRepositoryImpl
import dmitriy.losev.profile.data.repositories.PasswordRepositoryImpl
import dmitriy.losev.profile.data.repositories.PhoneNumberRepositoryImpl
import dmitriy.losev.profile.data.repositories.UriRepositoryImpl
import dmitriy.losev.profile.data.repositories.UserDataRepositoryImpl
import dmitriy.losev.profile.domain.repositories.AvatarRepository
import dmitriy.losev.profile.domain.repositories.EmailRepository
import dmitriy.losev.profile.domain.repositories.PasswordRepository
import dmitriy.losev.profile.domain.repositories.PhoneNumberRepository
import dmitriy.losev.profile.domain.repositories.UriRepository
import dmitriy.losev.profile.domain.repositories.UserDataRepository
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

    factoryOf(::PhoneNumberRepositoryImpl) {
        bind<PhoneNumberRepository>()
    }

    factoryOf(::UriRepositoryImpl) {
        bind<UriRepository>()
    }

    factoryOf(::UserDataRepositoryImpl) {
        bind<UserDataRepository>()
    }

    factoryOf(::AvatarRepositoryImpl) {
        bind<AvatarRepository>()
    }
}