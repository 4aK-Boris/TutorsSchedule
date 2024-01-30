package dmitriy.losev.database.domain.di

import dmitriy.losev.database.domain.usecases.contacts.DatabaseAddContactUseCase
import dmitriy.losev.database.domain.usecases.contacts.DatabaseDeleteContactUseCase
import dmitriy.losev.database.domain.usecases.contacts.DatabaseDeleteContactsUseCase
import dmitriy.losev.database.domain.usecases.contacts.DatabaseGetContactUseCase
import dmitriy.losev.database.domain.usecases.contacts.DatabaseSaveContactUseCase
import dmitriy.losev.database.domain.usecases.contacts.DatabaseSaveContactsUseCase
import dmitriy.losev.database.domain.usecases.contacts.DatabaseUpdateContactUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val databaseContactsUseCaseModule = module {

    factoryOf(::DatabaseAddContactUseCase)

    factoryOf(::DatabaseUpdateContactUseCase)

    factoryOf(::DatabaseDeleteContactUseCase)

    factoryOf(::DatabaseSaveContactsUseCase)

    factoryOf(::DatabaseGetContactUseCase)

    factoryOf(::DatabaseSaveContactUseCase)

    factoryOf(::DatabaseDeleteContactsUseCase)
}