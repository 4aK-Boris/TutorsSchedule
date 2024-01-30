package dmitriy.losev.database.domain.di

import dmitriy.losev.database.domain.usecases.subjects.DatabaseAddSubjectUseCase
import dmitriy.losev.database.domain.usecases.subjects.DatabaseDeleteSubjectUseCase
import dmitriy.losev.database.domain.usecases.subjects.DatabaseDeleteSubjectsUseCase
import dmitriy.losev.database.domain.usecases.subjects.DatabaseGetSubjectUseCase
import dmitriy.losev.database.domain.usecases.subjects.DatabaseGetSubjectsUseCase
import dmitriy.losev.database.domain.usecases.subjects.DatabaseSaveSubjectUseCase
import dmitriy.losev.database.domain.usecases.subjects.DatabaseSaveSubjectsUseCase
import dmitriy.losev.database.domain.usecases.subjects.DatabaseUpdateSubjectUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val databaseSubjectsUseCaseModule = module {

    factoryOf(::DatabaseAddSubjectUseCase)

    factoryOf(::DatabaseUpdateSubjectUseCase)

    factoryOf(::DatabaseDeleteSubjectUseCase)

    factoryOf(::DatabaseSaveSubjectsUseCase)

    factoryOf(::DatabaseGetSubjectUseCase)

    factoryOf(::DatabaseGetSubjectsUseCase)

    factoryOf(::DatabaseSaveSubjectUseCase)

    factoryOf(::DatabaseDeleteSubjectsUseCase)
}