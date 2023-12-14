package dmitriy.losev.firebase.core.di

import dmitriy.losev.firebase.data.di.firebaseMapperModule
import dmitriy.losev.firebase.data.di.firebaseRepositoryModule
import dmitriy.losev.firebase.domain.di.firebaseUseCaseModule
import org.koin.dsl.module

val firebaseModule = module {

    includes(firebaseCoreModule, firebaseUseCaseModule, firebaseMapperModule, firebaseRepositoryModule)
}