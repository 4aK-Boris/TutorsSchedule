package dmitriy.losev.database.data.di

import dmitriy.losev.database.data.mappers.ContactMapper
import dmitriy.losev.database.data.mappers.NameMapper
import dmitriy.losev.database.data.mappers.StudentMapper
import dmitriy.losev.database.data.mappers.SubjectMapper
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val databaseMapperModule = module {

    factoryOf(::NameMapper)

    factoryOf(::SubjectMapper)

    factoryOf(::ContactMapper)

    factoryOf(::StudentMapper)
}