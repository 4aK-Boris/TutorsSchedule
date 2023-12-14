package dmitriy.losev.firebase.domain.di

import dmitriy.losev.firebase.domain.usecases.storage.FirebaseDeleteAvatarStorageUseCase
import dmitriy.losev.firebase.domain.usecases.storage.FirebaseUploadAvatarStorageUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val firebaseStorageUseCaseModule = module {

    factoryOf(::FirebaseDeleteAvatarStorageUseCase)

    factoryOf(::FirebaseUploadAvatarStorageUseCase)
}