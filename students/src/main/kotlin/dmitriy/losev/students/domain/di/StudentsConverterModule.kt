package dmitriy.losev.students.domain.di

import dmitriy.losev.students.domain.converters.ActiveStudentConverter
import dmitriy.losev.students.domain.converters.ArchiveStudentConverter
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val studentsConverterModule = module {

    factoryOf(::ActiveStudentConverter)

    factoryOf(::ArchiveStudentConverter)
}