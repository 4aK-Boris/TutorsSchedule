package dmitriy.losev.database.domain.di

import dmitriy.losev.database.domain.usecases.groups.DatabaseAddGroupUseCase
import dmitriy.losev.database.domain.usecases.groups.DatabaseDeleteGroupUseCase
import dmitriy.losev.database.domain.usecases.groups.DatabaseDeleteGroupsUseCase
import dmitriy.losev.database.domain.usecases.groups.DatabaseGetGroupUseCase
import dmitriy.losev.database.domain.usecases.groups.DatabaseGetGroupsUseCase
import dmitriy.losev.database.domain.usecases.groups.DatabaseSaveGroupUseCase
import dmitriy.losev.database.domain.usecases.groups.DatabaseSaveGroupsUseCase
import dmitriy.losev.database.domain.usecases.groups.DatabaseUpdateGroupUseCase
import dmitriy.losev.database.domain.usecases.groups.students.DatabaseAddGroupStudentsUseCase
import dmitriy.losev.database.domain.usecases.groups.students.DatabaseDeleteGroupStudentsUseCase
import dmitriy.losev.database.domain.usecases.groups.students.DatabaseGetGroupStudentIdsUseCase
import dmitriy.losev.database.domain.usecases.groups.students.DatabaseGetGroupStudentNamesUseCase
import dmitriy.losev.database.domain.usecases.groups.students.DatabaseGetGroupStudentsUseCase
import dmitriy.losev.database.domain.usecases.groups.students.DatabaseSaveGroupStudentsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val databaseGroupsUseCaseModule = module {

    //group

    factoryOf(::DatabaseAddGroupUseCase)

    factoryOf(::DatabaseUpdateGroupUseCase)

    factoryOf(::DatabaseDeleteGroupUseCase)

    factoryOf(::DatabaseSaveGroupsUseCase)

    factoryOf(::DatabaseGetGroupUseCase)

    factoryOf(::DatabaseGetGroupsUseCase)

    factoryOf(::DatabaseSaveGroupUseCase)

    factoryOf(::DatabaseDeleteGroupsUseCase)

    //students

    factoryOf(::DatabaseAddGroupStudentsUseCase)

    factoryOf(::DatabaseGetGroupStudentsUseCase)

    factoryOf(::DatabaseSaveGroupStudentsUseCase)

    factoryOf(::DatabaseDeleteGroupStudentsUseCase)

    factoryOf(::DatabaseGetGroupStudentIdsUseCase)

    factoryOf(::DatabaseGetGroupStudentNamesUseCase)
}