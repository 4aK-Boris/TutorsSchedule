package dmitriy.losev.firebase.domain.di

import dmitriy.losev.firebase.domain.usecases.FirebaseAuthUseCases
import dmitriy.losev.firebase.domain.usecases.FirebaseStorageUseCases
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val firebaseUseCaseModule = module {

    factoryOf(::FirebaseStorageUseCases)

    factoryOf(::FirebaseAuthUseCases)
}