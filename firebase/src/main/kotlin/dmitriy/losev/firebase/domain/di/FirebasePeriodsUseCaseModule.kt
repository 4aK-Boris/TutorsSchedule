package dmitriy.losev.firebase.domain.di

import dmitriy.losev.firebase.domain.usecases.periods.FirebaseAddPeriodUseCase
import dmitriy.losev.firebase.domain.usecases.periods.FirebaseDeleteFullPeriodUseCase
import dmitriy.losev.firebase.domain.usecases.periods.FirebaseDeletePeriodUseCase
import dmitriy.losev.firebase.domain.usecases.periods.FirebaseGetPeriodUseCase
import dmitriy.losev.firebase.domain.usecases.periods.FirebaseUpdatePeriodUseCase
import dmitriy.losev.firebase.domain.usecases.periods.tasks.FirebaseAddPeriodTaskUseCase
import dmitriy.losev.firebase.domain.usecases.periods.tasks.FirebaseGetAllPeriodTasksUseCase
import dmitriy.losev.firebase.domain.usecases.periods.tasks.FirebaseRemoveAllPeriodTasksUseCase
import dmitriy.losev.firebase.domain.usecases.periods.tasks.FirebaseRemovePeriodTaskUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val firebasePeriodsUseCaseModule = module {
    
    //periods

    factoryOf(::FirebaseAddPeriodUseCase)

    factoryOf(::FirebaseDeletePeriodUseCase)

    factoryOf(::FirebaseGetPeriodUseCase)

    factoryOf(::FirebaseUpdatePeriodUseCase)

    factoryOf(::FirebaseDeleteFullPeriodUseCase)
    
    //tasks

    factoryOf(::FirebaseAddPeriodTaskUseCase)

    factoryOf(::FirebaseGetAllPeriodTasksUseCase)

    factoryOf(::FirebaseRemoveAllPeriodTasksUseCase)

    factoryOf(::FirebaseRemovePeriodTaskUseCase)
}