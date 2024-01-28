package dmitriy.losev.subjects.core

import dmitriy.losev.subjects.data.di.subjectMapperModule
import dmitriy.losev.subjects.data.di.subjectRepositoryModule
import dmitriy.losev.subjects.domain.di.subjectsUseCaseModule
import dmitriy.losev.subjects.presentation.di.subjectsViewModelModule
import org.koin.dsl.module

val subjectsModule = module {

    includes(subjectsViewModelModule, subjectsUseCaseModule, subjectRepositoryModule, subjectMapperModule)
}