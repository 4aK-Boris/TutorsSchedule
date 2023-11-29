package dmitriy.losev.profile.domain.di

import dmitriy.losev.profile.domain.usecases.ProfileChangePasswordUseCase
import dmitriy.losev.profile.domain.usecases.ProfileCheckEmailUseCase
import dmitriy.losev.profile.domain.usecases.ProfileCheckPasswordUseCase
import dmitriy.losev.profile.domain.usecases.ProfileDeleteAccountUseCase
import dmitriy.losev.profile.domain.usecases.ProfileEmailVerificationUseCase
import dmitriy.losev.profile.domain.usecases.ProfileLogInUseCase
import dmitriy.losev.profile.domain.usecases.ProfileLogOutUseCase
import dmitriy.losev.profile.domain.usecases.ProfileNavigationUseCases
import dmitriy.losev.profile.domain.usecases.ProfileUpdateAllUseCase
import dmitriy.losev.profile.domain.usecases.ProfileUpdateAvatarUseCase
import dmitriy.losev.profile.domain.usecases.ProfileUpdateDisplayNameUseCase
import dmitriy.losev.profile.domain.usecases.ProfileUpdateEmailUseCase
import dmitriy.losev.profile.domain.usecases.ProfileUserDataUseCase
import dmitriy.losev.profile.domain.usecases.ProfileUserUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val profileUseCaseModule = module {

    factoryOf(::ProfileNavigationUseCases)

    factoryOf(::ProfileUpdateEmailUseCase)

    factoryOf(::ProfileCheckEmailUseCase)

    factoryOf(::ProfileChangePasswordUseCase)

    factoryOf(::ProfileCheckPasswordUseCase)

    factoryOf(::ProfileUpdateAvatarUseCase)

    factoryOf(::ProfileUpdateAllUseCase)

    factoryOf(::ProfileUpdateDisplayNameUseCase)

    factoryOf(::ProfileEmailVerificationUseCase)

    factoryOf(::ProfileDeleteAccountUseCase)

    factoryOf(::ProfileUserUseCase)

    factoryOf(::ProfileUserDataUseCase)

    factoryOf(::ProfileLogInUseCase)

    factoryOf(::ProfileLogOutUseCase)
}