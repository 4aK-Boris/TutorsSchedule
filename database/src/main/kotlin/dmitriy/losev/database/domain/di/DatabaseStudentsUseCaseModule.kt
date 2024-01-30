package dmitriy.losev.database.domain.di

import dmitriy.losev.database.domain.usecases.students.DatabaseAddStudentUseCase
import dmitriy.losev.database.domain.usecases.students.DatabaseDeleteStudentUseCase
import dmitriy.losev.database.domain.usecases.students.DatabaseDeleteStudentsUseCase
import dmitriy.losev.database.domain.usecases.students.DatabaseGetStudentNamesUseCase
import dmitriy.losev.database.domain.usecases.students.DatabaseGetStudentUseCase
import dmitriy.losev.database.domain.usecases.students.DatabaseGetStudentsUseCase
import dmitriy.losev.database.domain.usecases.students.DatabaseSaveStudentUseCase
import dmitriy.losev.database.domain.usecases.students.DatabaseSaveStudentsUseCase
import dmitriy.losev.database.domain.usecases.students.DatabaseUpdateStudentUseCase
import dmitriy.losev.database.domain.usecases.students.contacts.DatabaseGetStudentContactsUseCase
import dmitriy.losev.database.domain.usecases.students.groups.DatabaseDeleteStudentGroupsUseCase
import dmitriy.losev.database.domain.usecases.students.groups.DatabaseGetStudentGroupsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val databaseStudentsUseCaseModule = module {

    //student

    factoryOf(::DatabaseAddStudentUseCase)

    factoryOf(::DatabaseUpdateStudentUseCase)

    factoryOf(::DatabaseDeleteStudentUseCase)

    factoryOf(::DatabaseSaveStudentsUseCase)

    factoryOf(::DatabaseGetStudentUseCase)

    factoryOf(::DatabaseGetStudentsUseCase)

    factoryOf(::DatabaseSaveStudentUseCase)

    factoryOf(::DatabaseGetStudentNamesUseCase)

    factoryOf(::DatabaseDeleteStudentsUseCase)

    //contacts

    factoryOf(::DatabaseGetStudentContactsUseCase)

    //groups

    factoryOf(::DatabaseGetStudentGroupsUseCase)

    factoryOf(::DatabaseDeleteStudentGroupsUseCase)
}