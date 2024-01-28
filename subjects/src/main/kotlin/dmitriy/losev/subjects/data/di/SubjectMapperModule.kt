package dmitriy.losev.subjects.data.di

import dmitriy.losev.subjects.data.mappers.SubjectMapper
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val subjectMapperModule = module {

    factoryOf(::SubjectMapper)
}