package dmitriy.losev.database.data.di

import dmitriy.losev.database.data.repositories.ContactsRepositoryImpl
import dmitriy.losev.database.data.repositories.StudentsRepositoryImpl
import dmitriy.losev.database.data.repositories.SubjectsRepositoryImpl
import dmitriy.losev.database.domain.repositories.ContactsRepository
import dmitriy.losev.database.domain.repositories.StudentsRepository
import dmitriy.losev.database.domain.repositories.SubjectsRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val databaseRepositoryModule = module {

    factoryOf(::SubjectsRepositoryImpl) {
        bind<SubjectsRepository>()
    }

    factoryOf(::ContactsRepositoryImpl) {
        bind<ContactsRepository>()
    }

    factoryOf(::StudentsRepositoryImpl) {
        bind<StudentsRepository>()
    }
}