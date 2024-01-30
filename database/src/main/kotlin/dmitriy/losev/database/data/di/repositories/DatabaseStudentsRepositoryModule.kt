package dmitriy.losev.database.data.di.repositories

import dmitriy.losev.database.data.repositories.students.StudentContactsRepositoryImpl
import dmitriy.losev.database.data.repositories.students.StudentGroupsRepositoryImpl
import dmitriy.losev.database.data.repositories.students.StudentsRepositoryImpl
import dmitriy.losev.database.domain.repositories.students.StudentContactsRepository
import dmitriy.losev.database.domain.repositories.students.StudentGroupsRepository
import dmitriy.losev.database.domain.repositories.students.StudentsRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val databaseStudentsRepositoryModule = module {

    factoryOf(::StudentsRepositoryImpl) {
        bind<StudentsRepository>()
    }

    factoryOf(::StudentContactsRepositoryImpl) {
        bind<StudentContactsRepository>()
    }

    factoryOf(::StudentGroupsRepositoryImpl) {
        bind<StudentGroupsRepository>()
    }
}