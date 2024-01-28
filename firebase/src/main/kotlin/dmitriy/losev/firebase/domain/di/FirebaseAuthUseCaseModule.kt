package dmitriy.losev.firebase.domain.di

import dmitriy.losev.firebase.domain.usecases.auth.FirebaseActivityAuthUseCase
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseAuthUseCase
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseEmailAuthUseCase
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseEmailRegistrationUseCase
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseGoogleAuthUseCase
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseLogOutUseCase
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseProviderUseCase
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseTokenAuthUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val firebaseAuthUseCaseModule = module {

    factoryOf(::FirebaseActivityAuthUseCase)

    factoryOf(::FirebaseAuthUseCase)

    factoryOf(::FirebaseEmailAuthUseCase)

    factoryOf(::FirebaseEmailRegistrationUseCase)

    factoryOf(::FirebaseGoogleAuthUseCase)

    factoryOf(::FirebaseLogOutUseCase)

    factoryOf(::FirebaseProviderUseCase)

    factoryOf(::FirebaseTokenAuthUseCase)
}