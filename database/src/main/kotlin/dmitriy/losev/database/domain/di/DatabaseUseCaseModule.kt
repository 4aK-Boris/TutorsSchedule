package dmitriy.losev.database.domain.di

import org.koin.dsl.module

val databaseUseCaseModule = module {

    includes(
        databaseSubjectsUseCaseModule,
        databaseStudentsUseCaseModule,
        databaseContactsUseCaseModule
    )
}