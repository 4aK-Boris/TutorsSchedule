package dmitriy.losev.students.domain.di

import dmitriy.losev.students.domain.usecases.tasks.GetStudentTasksUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val studentTaskUseCaseModule = module {

    factoryOf(::GetStudentTasksUseCase)
}