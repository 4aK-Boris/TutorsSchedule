package dmitriy.losev.firebase.data.di.repositories

import dmitriy.losev.firebase.data.repositories.students.FirebaseStudentGroupsRepositoryImpl
import dmitriy.losev.firebase.data.repositories.students.FirebaseStudentLessonsRepositoryImpl
import dmitriy.losev.firebase.data.repositories.students.FirebaseStudentTasksRepositoryImpl
import dmitriy.losev.firebase.data.repositories.students.FirebaseStudentsRepositoryImpl
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentGroupsRepository
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentLessonsRepository
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentTasksRepository
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentsRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val firebaseStudentsRepositoryModule = module {

    factoryOf(::FirebaseStudentsRepositoryImpl) {
        bind<FirebaseStudentsRepository>()
    }

    factoryOf(::FirebaseStudentGroupsRepositoryImpl) {
        bind<FirebaseStudentGroupsRepository>()
    }

    factoryOf(::FirebaseStudentLessonsRepositoryImpl) {
        bind<FirebaseStudentLessonsRepository>()
    }

    factoryOf(::FirebaseStudentTasksRepositoryImpl) {
        bind<FirebaseStudentTasksRepository>()
    }
}