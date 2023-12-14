package dmitriy.losev.firebase.data.di.repositories

import dmitriy.losev.firebase.data.repositories.lessons.FirebaseLessonPeriodsRepositoryImpl
import dmitriy.losev.firebase.data.repositories.lessons.FirebaseLessonTasksRepositoryImpl
import dmitriy.losev.firebase.data.repositories.lessons.FirebaseLessonsRepositoryImpl
import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonPeriodsRepository
import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonTasksRepository
import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonsRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val firebaseLessonsRepositoryModule = module {

    factoryOf(::FirebaseLessonsRepositoryImpl) {
        bind<FirebaseLessonsRepository>()
    }

    factoryOf(::FirebaseLessonTasksRepositoryImpl) {
        bind<FirebaseLessonTasksRepository>()
    }

    factoryOf(::FirebaseLessonPeriodsRepositoryImpl) {
        bind<FirebaseLessonPeriodsRepository>()
    }
}