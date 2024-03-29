package dmitriy.losev.firebase.domain.di

import dmitriy.losev.firebase.domain.usecases.user.FirebaseChangePasswordUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseDeleteUserUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseEmailVerificationUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseEmailVerifiedUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetAvatarUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetDisplayNameUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetEmailUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetFirstNameUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetFormattedPhoneNumberUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetLastNameUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetPatronymicUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetPhoneNumberUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserIdUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseResetPasswordUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdateAvatarUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdateEmailUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdateFirstNameUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdateLastNameUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdatePatronymicUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdatePhoneNumberUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val firebaseUserUseCaseModule = module {

    factoryOf(::FirebaseDeleteUserUseCase)

    factoryOf(::FirebaseEmailVerificationUseCase)

    factoryOf(::FirebaseEmailVerifiedUseCase)

    factoryOf(::FirebaseGetAvatarUseCase)

    factoryOf(::FirebaseGetDisplayNameUseCase)

    factoryOf(::FirebaseGetEmailUseCase)

    factoryOf(::FirebaseGetFirstNameUseCase)

    factoryOf(::FirebaseGetLastNameUseCase)

    factoryOf(::FirebaseGetUserUseCase)

    factoryOf(::FirebaseChangePasswordUseCase)

    factoryOf(::FirebaseResetPasswordUseCase)

    factoryOf(::FirebaseUpdateAvatarUseCase)

    factoryOf(::FirebaseUpdateEmailUseCase)

    factoryOf(::FirebaseGetPatronymicUseCase)

    factoryOf(::FirebaseGetPhoneNumberUseCase)

    factoryOf(::FirebaseUpdateFirstNameUseCase)

    factoryOf(::FirebaseUpdateLastNameUseCase)

    factoryOf(::FirebaseUpdatePatronymicUseCase)

    factoryOf(::FirebaseUpdatePhoneNumberUseCase)

    factoryOf(::FirebaseGetFormattedPhoneNumberUseCase)

    factoryOf(::FirebaseGetUserIdUseCase)
}