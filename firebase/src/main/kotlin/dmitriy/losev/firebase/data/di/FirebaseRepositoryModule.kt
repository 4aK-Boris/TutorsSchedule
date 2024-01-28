package dmitriy.losev.firebase.data.di

import dmitriy.losev.firebase.data.di.repositories.firebaseGroupsRepositoryModule
import dmitriy.losev.firebase.data.di.repositories.firebaseLessonsRepositoryModule
import dmitriy.losev.firebase.data.di.repositories.firebasePeriodsRepositoryModule
import dmitriy.losev.firebase.data.di.repositories.firebaseStudentsRepositoryModule
import dmitriy.losev.firebase.data.di.repositories.firebaseSubjectsRepositoryModule
import dmitriy.losev.firebase.data.di.repositories.firebaseTasksRepositoryModule
import dmitriy.losev.firebase.data.repositories.FirebaseContactsRepositoryImpl
import dmitriy.losev.firebase.data.repositories.FirebaseUserDataRepositoryImpl
import dmitriy.losev.firebase.data.repositories.FirebaseUriRepositoryImpl
import dmitriy.losev.firebase.domain.repositories.FirebaseContactsRepository
import dmitriy.losev.firebase.domain.repositories.FirebaseUserDataRepository
import dmitriy.losev.firebase.domain.repositories.FirebaseUriRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val firebaseRepositoryModule = module {

    includes(
        firebaseGroupsRepositoryModule,
        firebaseStudentsRepositoryModule,
        firebaseSubjectsRepositoryModule,
        firebaseLessonsRepositoryModule,
        firebaseTasksRepositoryModule,
        firebasePeriodsRepositoryModule
    )

    factoryOf(::FirebaseContactsRepositoryImpl) {
        bind<FirebaseContactsRepository>()
    }

    factoryOf(::FirebaseUserDataRepositoryImpl) {
        bind<FirebaseUserDataRepository>()
    }

    factoryOf(::FirebaseUriRepositoryImpl) {
        bind<FirebaseUriRepository>()
    }
}