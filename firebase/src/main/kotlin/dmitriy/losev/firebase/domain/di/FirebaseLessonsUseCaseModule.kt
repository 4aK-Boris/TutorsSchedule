package dmitriy.losev.firebase.domain.di

import dmitriy.losev.firebase.domain.usecases.lessons.FirebaseAddLessonUseCase
import dmitriy.losev.firebase.domain.usecases.lessons.FirebaseDeleteLessonUseCase
import dmitriy.losev.firebase.domain.usecases.lessons.FirebaseGetLessonUseCase
import dmitriy.losev.firebase.domain.usecases.lessons.FirebaseGetLessonsUseCase
import dmitriy.losev.firebase.domain.usecases.lessons.FirebaseUpdateLessonUseCase
import dmitriy.losev.firebase.domain.usecases.lessons.periods.FirebaseAddLessonPeriodUseCase
import dmitriy.losev.firebase.domain.usecases.lessons.periods.FirebaseGetAllLessonPeriodsUseCase
import dmitriy.losev.firebase.domain.usecases.lessons.periods.FirebaseRemoveAllLessonPeriodsUseCase
import dmitriy.losev.firebase.domain.usecases.lessons.periods.FirebaseRemoveLessonPeriodUseCase
import dmitriy.losev.firebase.domain.usecases.lessons.tasks.FirebaseAddLessonTaskUseCase
import dmitriy.losev.firebase.domain.usecases.lessons.tasks.FirebaseGetAllLessonTasksUseCase
import dmitriy.losev.firebase.domain.usecases.lessons.tasks.FirebaseRemoveAllLessonTasksUseCase
import dmitriy.losev.firebase.domain.usecases.lessons.tasks.FirebaseRemoveLessonTaskUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val firebaseLessonsUseCaseModule = module {
    
    //lessons

    factoryOf(::FirebaseAddLessonUseCase)

    factoryOf(::FirebaseDeleteLessonUseCase)

    factoryOf(::FirebaseGetLessonsUseCase)

    factoryOf(::FirebaseGetLessonUseCase)

    factoryOf(::FirebaseUpdateLessonUseCase)
    
    //tasks

    factoryOf(::FirebaseAddLessonTaskUseCase)

    factoryOf(::FirebaseGetAllLessonTasksUseCase)

    factoryOf(::FirebaseRemoveAllLessonTasksUseCase)

    factoryOf(::FirebaseRemoveLessonTaskUseCase)
    
    //periods

    factoryOf(::FirebaseAddLessonPeriodUseCase)

    factoryOf(::FirebaseGetAllLessonPeriodsUseCase)

    factoryOf(::FirebaseRemoveAllLessonPeriodsUseCase)

    factoryOf(::FirebaseRemoveLessonPeriodUseCase)
}