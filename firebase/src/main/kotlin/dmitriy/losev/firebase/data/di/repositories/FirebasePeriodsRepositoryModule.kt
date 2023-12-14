package dmitriy.losev.firebase.data.di.repositories

import dmitriy.losev.firebase.data.repositories.periods.FirebasePeriodTasksRepositoryImpl
import dmitriy.losev.firebase.data.repositories.periods.FirebasePeriodsRepositoryImpl
import dmitriy.losev.firebase.domain.repositories.periods.FirebasePeriodTasksRepository
import dmitriy.losev.firebase.domain.repositories.periods.FirebasePeriodsRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val firebasePeriodsRepositoryModule = module {

    factoryOf(::FirebasePeriodsRepositoryImpl) {
        bind<FirebasePeriodsRepository>()
    }

    factoryOf(::FirebasePeriodTasksRepositoryImpl) {
        bind<FirebasePeriodTasksRepository>()
    }
}