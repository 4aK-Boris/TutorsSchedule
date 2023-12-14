package dmitriy.losev.firebase.data.di.repositories

import dmitriy.losev.firebase.data.repositories.groups.FirebaseGroupLessonsRepositoryImpl
import dmitriy.losev.firebase.data.repositories.groups.FirebaseGroupStudentsRepositoryImpl
import dmitriy.losev.firebase.data.repositories.groups.FirebaseGroupTasksRepositoryImpl
import dmitriy.losev.firebase.data.repositories.groups.FirebaseGroupsRepositoryImpl
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupLessonsRepository
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupStudentsRepository
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupTasksRepository
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupsRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val firebaseGroupsRepositoryModule = module {

    factoryOf(::FirebaseGroupsRepositoryImpl) {
        bind<FirebaseGroupsRepository>()
    }

    factoryOf(::FirebaseGroupStudentsRepositoryImpl) {
        bind<FirebaseGroupStudentsRepository>()
    }

    factoryOf(::FirebaseGroupLessonsRepositoryImpl) {
        bind<FirebaseGroupLessonsRepository>()
    }

    factoryOf(::FirebaseGroupTasksRepositoryImpl) {
        bind<FirebaseGroupTasksRepository>()
    }
}