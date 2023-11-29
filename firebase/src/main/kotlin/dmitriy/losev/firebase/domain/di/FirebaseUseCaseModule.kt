package dmitriy.losev.firebase.domain.di

import dmitriy.losev.firebase.domain.usecases.FirebaseActivityAuthUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseAddStudentUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseAvatarUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseDeleteAccountUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseDeleteAvatarStorageUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseDisplayNameUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseEmailAuthUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseEmailRegistrationUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseEmailUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseEmailVerificationUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseEmailVerifiedUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseFirstNameUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseGoogleAuthUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseLastNameUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseLogOutUseCase
import dmitriy.losev.firebase.domain.usecases.FirebasePasswordUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseProviderUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseResetPasswordUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseStudentUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseStudentsUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseTokenAuthUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseUpdateAvatarUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseUpdateDisplayNameUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseUpdateEmailUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseUpdateStudentUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseUploadAvatarStorageUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseUserUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val firebaseUseCaseModule = module {

    factoryOf(::FirebaseActivityAuthUseCase)

    factoryOf(::FirebaseAvatarUseCase)

    factoryOf(::FirebaseDeleteAccountUseCase)

    factoryOf(::FirebaseDeleteAvatarStorageUseCase)

    factoryOf(::FirebaseDisplayNameUseCase)

    factoryOf(::FirebaseEmailAuthUseCase)

    factoryOf(::FirebaseEmailRegistrationUseCase)

    factoryOf(::FirebaseEmailUseCase)

    factoryOf(::FirebaseEmailVerificationUseCase)

    factoryOf(::FirebaseEmailVerifiedUseCase)

    factoryOf(::FirebaseFirstNameUseCase)

    factoryOf(::FirebaseGoogleAuthUseCase)

    factoryOf(::FirebaseLastNameUseCase)

    factoryOf(::FirebaseLogOutUseCase)

    factoryOf(::FirebasePasswordUseCase)

    factoryOf(::FirebaseProviderUseCase)

    factoryOf(::FirebaseResetPasswordUseCase)

    factoryOf(::FirebaseTokenAuthUseCase)

    factoryOf(::FirebaseUpdateAvatarUseCase)

    factoryOf(::FirebaseUpdateDisplayNameUseCase)

    factoryOf(::FirebaseUpdateEmailUseCase)

    factoryOf(::FirebaseUploadAvatarStorageUseCase)

    factoryOf(::FirebaseUserUseCase)

    factoryOf(::FirebaseAddStudentUseCase)

    factoryOf(::FirebaseStudentsUseCase)

    factoryOf(::FirebaseStudentUseCase)

    factoryOf(::FirebaseUpdateStudentUseCase)
}