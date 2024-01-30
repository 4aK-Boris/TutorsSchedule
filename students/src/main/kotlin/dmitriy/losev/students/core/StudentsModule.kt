package dmitriy.losev.students.core

import dmitriy.losev.students.data.di.studentsMapperModule
import dmitriy.losev.students.data.di.studentsRepositoryModule
import dmitriy.losev.students.domain.di.studentUseCaseModule
import dmitriy.losev.students.domain.di.studentsConverterModule
import dmitriy.losev.students.presentation.di.studentsViewModelModule
import org.koin.dsl.module

val studentModule = module {

    includes(
        studentUseCaseModule,
        studentsViewModelModule,
        studentsMapperModule,
        studentsRepositoryModule,
        studentsConverterModule
    )

    single {
        StudentsHandler()
    }
}