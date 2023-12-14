package dmitriy.losev.firebase.domain.di

import dmitriy.losev.firebase.domain.usecases.subjects.FirebaseAddSubjectUseCase
import dmitriy.losev.firebase.domain.usecases.subjects.FirebaseDeleteSubjectUseCase
import dmitriy.losev.firebase.domain.usecases.subjects.FirebaseGetSubjectUseCase
import dmitriy.losev.firebase.domain.usecases.subjects.FirebaseGetSubjectsUseCase
import dmitriy.losev.firebase.domain.usecases.subjects.FirebaseUpdateSubjectUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val firebaseSubjectsUseCaseModule = module {

    factoryOf(::FirebaseAddSubjectUseCase)

    factoryOf(::FirebaseDeleteSubjectUseCase)

    factoryOf(::FirebaseGetSubjectsUseCase)

    factoryOf(::FirebaseGetSubjectUseCase)

    factoryOf(::FirebaseUpdateSubjectUseCase)
}