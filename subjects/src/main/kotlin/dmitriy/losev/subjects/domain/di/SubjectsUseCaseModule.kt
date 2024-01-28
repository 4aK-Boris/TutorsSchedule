package dmitriy.losev.subjects.domain.di

import dmitriy.losev.subjects.domain.usecases.SubjectsAddSubjectUseCase
import dmitriy.losev.subjects.domain.usecases.SubjectsCheckNameUseCase
import dmitriy.losev.subjects.domain.usecases.SubjectsDeleteSubjectUseCase
import dmitriy.losev.subjects.domain.usecases.SubjectsGetSubjectUseCase
import dmitriy.losev.subjects.domain.usecases.SubjectsGetSubjectsUseCase
import dmitriy.losev.subjects.domain.usecases.SubjectsNavigationUseCase
import dmitriy.losev.subjects.domain.usecases.SubjectsUpdateSubjectUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val subjectsUseCaseModule = module {

    factoryOf(::SubjectsNavigationUseCase)

    factoryOf(::SubjectsAddSubjectUseCase)

    factoryOf(::SubjectsUpdateSubjectUseCase)

    factoryOf(::SubjectsDeleteSubjectUseCase)

    factoryOf(::SubjectsGetSubjectsUseCase)

    factoryOf(::SubjectsGetSubjectUseCase)

    factoryOf(::SubjectsCheckNameUseCase)
}