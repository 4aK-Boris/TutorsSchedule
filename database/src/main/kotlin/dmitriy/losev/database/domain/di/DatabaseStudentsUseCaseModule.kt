package dmitriy.losev.database.domain.di

import dmitriy.losev.database.domain.usecases.students.DatabaseAddStudentUseCase
import dmitriy.losev.database.domain.usecases.students.DatabaseDeleteFullStudentUseCase
import dmitriy.losev.database.domain.usecases.students.DatabaseDeleteStudentUseCase
import dmitriy.losev.database.domain.usecases.students.DatabaseGetStudentUseCase
import dmitriy.losev.database.domain.usecases.students.DatabaseGetStudentsUseCase
import dmitriy.losev.database.domain.usecases.students.DatabaseSaveStudentUseCase
import dmitriy.losev.database.domain.usecases.students.DatabaseSaveStudentsUseCase
import dmitriy.losev.database.domain.usecases.students.DatabaseUpdateStudentUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val databaseStudentsUseCaseModule = module {

    factoryOf(::DatabaseAddStudentUseCase)

    factoryOf(::DatabaseUpdateStudentUseCase)

    factoryOf(::DatabaseDeleteStudentUseCase)

    factoryOf(::DatabaseSaveStudentsUseCase)

    factoryOf(::DatabaseGetStudentUseCase)

    factoryOf(::DatabaseGetStudentsUseCase)

    factoryOf(::DatabaseSaveStudentUseCase)

    factoryOf(::DatabaseDeleteFullStudentUseCase)
}