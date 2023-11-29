package dmitriy.losev.firebase.data.di

import dmitriy.losev.firebase.data.repositories.FirebaseStudentRepositoryImpl
import dmitriy.losev.firebase.data.repositories.FirebaseStudentsRepositoryImpl
import dmitriy.losev.firebase.domain.repositories.FirebaseStudentRepository
import dmitriy.losev.firebase.domain.repositories.FirebaseStudentsRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val firebaseRepositoryModule = module {

    factoryOf(::FirebaseStudentRepositoryImpl) {
        bind<FirebaseStudentRepository>()
    }

    factoryOf(::FirebaseStudentsRepositoryImpl) {
        bind<FirebaseStudentsRepository>()
    }
}