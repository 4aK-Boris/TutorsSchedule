package dmitriy.losev.firebase.domain.di

import dmitriy.losev.firebase.domain.usecases.groups.FirebaseAddGroupUseCase
import dmitriy.losev.firebase.domain.usecases.groups.FirebaseDeleteFullGroupUseCase
import dmitriy.losev.firebase.domain.usecases.groups.FirebaseDeleteGroupUseCase
import dmitriy.losev.firebase.domain.usecases.groups.FirebaseGetGroupUseCase
import dmitriy.losev.firebase.domain.usecases.groups.FirebaseGetGroupsUseCase
import dmitriy.losev.firebase.domain.usecases.groups.FirebaseUpdateGroupUseCase
import dmitriy.losev.firebase.domain.usecases.groups.lessons.FirebaseAddGroupLessonUseCase
import dmitriy.losev.firebase.domain.usecases.groups.lessons.FirebaseGetAllGroupLessonsUseCase
import dmitriy.losev.firebase.domain.usecases.groups.lessons.FirebaseRemoveAllGroupLessonsUseCase
import dmitriy.losev.firebase.domain.usecases.groups.lessons.FirebaseRemoveGroupLessonUseCase
import dmitriy.losev.firebase.domain.usecases.groups.students.FirebaseAddGroupStudentUseCase
import dmitriy.losev.firebase.domain.usecases.groups.students.FirebaseAddGroupStudentsUseCase
import dmitriy.losev.firebase.domain.usecases.groups.students.FirebaseDeleteGroupStudentsUseCase
import dmitriy.losev.firebase.domain.usecases.groups.students.FirebaseGetGroupStudentIdsUseCase
import dmitriy.losev.firebase.domain.usecases.groups.students.FirebaseGetGroupStudentNamesUseCase
import dmitriy.losev.firebase.domain.usecases.groups.students.FirebaseGetGroupStudentsUseCase
import dmitriy.losev.firebase.domain.usecases.groups.students.FirebaseRemoveGroupStudentUseCase
import dmitriy.losev.firebase.domain.usecases.groups.students.FirebaseRemoveGroupStudentsUseCase
import dmitriy.losev.firebase.domain.usecases.groups.tasks.FirebaseAddGroupTaskUseCase
import dmitriy.losev.firebase.domain.usecases.groups.tasks.FirebaseGetAllGroupTasksUseCase
import dmitriy.losev.firebase.domain.usecases.groups.tasks.FirebaseRemoveAllGroupTasksUseCase
import dmitriy.losev.firebase.domain.usecases.groups.tasks.FirebaseRemoveGroupTaskUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val firebaseGroupsUseCaseModule = module {

    //groups

    factoryOf(::FirebaseAddGroupUseCase)

    factoryOf(::FirebaseDeleteGroupUseCase)

    factoryOf(::FirebaseGetGroupsUseCase)

    factoryOf(::FirebaseGetGroupUseCase)

    factoryOf(::FirebaseUpdateGroupUseCase)

    factoryOf(::FirebaseDeleteFullGroupUseCase)

    //student

    factoryOf(::FirebaseAddGroupStudentUseCase)

    factoryOf(::FirebaseGetGroupStudentIdsUseCase)

    factoryOf(::FirebaseRemoveGroupStudentsUseCase)

    factoryOf(::FirebaseRemoveGroupStudentUseCase)

    factoryOf(::FirebaseAddGroupStudentsUseCase)

    factoryOf(::FirebaseDeleteGroupStudentsUseCase)

    factoryOf(::FirebaseGetGroupStudentNamesUseCase)

    factoryOf(::FirebaseGetGroupStudentsUseCase)

    //lessons

    factoryOf(::FirebaseAddGroupLessonUseCase)

    factoryOf(::FirebaseGetAllGroupLessonsUseCase)

    factoryOf(::FirebaseRemoveAllGroupLessonsUseCase)

    factoryOf(::FirebaseRemoveGroupLessonUseCase)

    //tasks

    factoryOf(::FirebaseAddGroupTaskUseCase)

    factoryOf(::FirebaseGetAllGroupTasksUseCase)

    factoryOf(::FirebaseRemoveAllGroupTasksUseCase)

    factoryOf(::FirebaseRemoveGroupTaskUseCase)
}