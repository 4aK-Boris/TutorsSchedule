package dmitriy.losev.students.domain.di

import dmitriy.losev.students.domain.usecases.StudentsCheckNameUseCase
import dmitriy.losev.students.domain.usecases.StudentsAuthUseCase
import dmitriy.losev.students.domain.usecases.StudentsCheckEmailUseCase
import dmitriy.losev.students.domain.usecases.StudentsCheckPhoneNumberUseCase
import dmitriy.losev.students.domain.usecases.StudentsNavigationUseCases
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val studentUseCaseModule = module {

    includes(
        studentsContactsUseCaseModule,
        studentsContactUseCaseModule,
        studentsGetStudentUseCaseModule,
        studentGroupsUseCaseModule,
        studentLessonsUseCaseModule,
        studentTaskUseCaseModule
    )

    factoryOf(::StudentsNavigationUseCases)

    factoryOf(::StudentsCheckEmailUseCase)

    factoryOf(::StudentsCheckPhoneNumberUseCase)

    factoryOf(::StudentsCheckNameUseCase)

    factoryOf(::StudentsAuthUseCase)
}