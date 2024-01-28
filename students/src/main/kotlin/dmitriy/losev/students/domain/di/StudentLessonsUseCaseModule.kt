package dmitriy.losev.students.domain.di

import dmitriy.losev.students.domain.usecases.lessons.GetStudentLessonsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val studentLessonsUseCaseModule = module {

    factoryOf(::GetStudentLessonsUseCase)
}