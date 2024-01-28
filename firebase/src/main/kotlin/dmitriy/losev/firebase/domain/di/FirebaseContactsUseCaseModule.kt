package dmitriy.losev.firebase.domain.di

import dmitriy.losev.firebase.domain.usecases.contacts.FirebaseAddContactUseCase
import dmitriy.losev.firebase.domain.usecases.contacts.FirebaseDeleteContactUseCase
import dmitriy.losev.firebase.domain.usecases.contacts.FirebaseGetContactUseCase
import dmitriy.losev.firebase.domain.usecases.contacts.FirebaseGetContactsUseCase
import dmitriy.losev.firebase.domain.usecases.contacts.FirebaseUpdateContactUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val firebaseContactsUseCaseModule = module {

    factoryOf(::FirebaseAddContactUseCase)

    factoryOf(::FirebaseUpdateContactUseCase)

    factoryOf(::FirebaseGetContactUseCase)

    factoryOf(::FirebaseDeleteContactUseCase)

    factoryOf(::FirebaseGetContactsUseCase)
}