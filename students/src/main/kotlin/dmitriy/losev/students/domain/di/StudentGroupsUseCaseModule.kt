package dmitriy.losev.students.domain.di

import dmitriy.losev.students.domain.usecases.groups.GetStudentGroupsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val studentGroupsUseCaseModule = module {

    factoryOf(::GetStudentGroupsUseCase)
}