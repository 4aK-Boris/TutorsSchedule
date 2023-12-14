package dmitriy.losev.firebase.domain.di

import dmitriy.losev.firebase.domain.usecases.FirebaseUriUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val firebaseUseCaseModule = module {

    includes(
        firebaseGetSimpleStudentsUseCaseModule,
        firebaseContactsUseCaseModule,
        firebaseAuthUseCaseModule,
        firebaseStorageUseCaseModule,
        firebaseUserUseCaseModule,
        firebaseGroupsUseCaseModule,
        firebaseSubjectsUseCaseModule,
        firebaseLessonsUseCaseModule,
        firebasePeriodsUseCaseModule,
        firebaseTasksUseCaseModule
    )

    factoryOf(::FirebaseUriUseCase)
}