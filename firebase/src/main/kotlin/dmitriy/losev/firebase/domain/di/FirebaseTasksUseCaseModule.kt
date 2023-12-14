package dmitriy.losev.firebase.domain.di

import dmitriy.losev.firebase.domain.usecases.tasks.FirebaseAddTaskUseCase
import dmitriy.losev.firebase.domain.usecases.tasks.FirebaseDeleteTaskUseCase
import dmitriy.losev.firebase.domain.usecases.tasks.FirebaseGetTaskUseCase
import dmitriy.losev.firebase.domain.usecases.tasks.FirebaseGetTasksUseCase
import dmitriy.losev.firebase.domain.usecases.tasks.FirebaseUpdateTaskUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val firebaseTasksUseCaseModule = module {

    factoryOf(::FirebaseAddTaskUseCase)

    factoryOf(::FirebaseDeleteTaskUseCase)

    factoryOf(::FirebaseGetTasksUseCase)

    factoryOf(::FirebaseGetTaskUseCase)

    factoryOf(::FirebaseUpdateTaskUseCase)
}