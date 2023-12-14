package dmitriy.losev.firebase.domain.di

import dmitriy.losev.firebase.domain.usecases.students.FirebaseAddStudentUseCase
import dmitriy.losev.firebase.domain.usecases.students.FirebaseDeleteStudentUseCase
import dmitriy.losev.firebase.domain.usecases.students.FirebaseGetSimpleStudentsUseCase
import dmitriy.losev.firebase.domain.usecases.students.FirebaseGetStudentUseCase
import dmitriy.losev.firebase.domain.usecases.students.FirebaseUpdateStudentUseCase
import dmitriy.losev.firebase.domain.usecases.students.groups.FirebaseAddStudentGroupUseCase
import dmitriy.losev.firebase.domain.usecases.students.groups.FirebaseGetAllStudentGroupsUseCase
import dmitriy.losev.firebase.domain.usecases.students.groups.FirebaseRemoveAllStudentGroupsUseCase
import dmitriy.losev.firebase.domain.usecases.students.groups.FirebaseRemoveStudentGroupUseCase
import dmitriy.losev.firebase.domain.usecases.students.lessons.FirebaseAddStudentLessonUseCase
import dmitriy.losev.firebase.domain.usecases.students.lessons.FirebaseGetAllStudentLessonsUseCase
import dmitriy.losev.firebase.domain.usecases.students.lessons.FirebaseRemoveAllStudentLessonsUseCase
import dmitriy.losev.firebase.domain.usecases.students.lessons.FirebaseRemoveStudentLessonUseCase
import dmitriy.losev.firebase.domain.usecases.students.tasks.FirebaseAddStudentTaskUseCase
import dmitriy.losev.firebase.domain.usecases.students.tasks.FirebaseGetAllStudentTasksUseCase
import dmitriy.losev.firebase.domain.usecases.students.tasks.FirebaseRemoveAllStudentTasksUseCase
import dmitriy.losev.firebase.domain.usecases.students.tasks.FirebaseRemoveStudentTaskUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val firebaseGetSimpleStudentsUseCaseModule = module {

    factoryOf(::FirebaseDeleteStudentUseCase)

    factoryOf(::FirebaseAddStudentUseCase)

    factoryOf(::FirebaseGetStudentUseCase)

    factoryOf(::FirebaseUpdateStudentUseCase)

    factoryOf(::FirebaseGetSimpleStudentsUseCase)

    //groups

    factoryOf(::FirebaseAddStudentGroupUseCase)

    factoryOf(::FirebaseGetAllStudentGroupsUseCase)

    factoryOf(::FirebaseRemoveAllStudentGroupsUseCase)

    factoryOf(::FirebaseRemoveStudentGroupUseCase)

    //lessons

    factoryOf(::FirebaseAddStudentLessonUseCase)

    factoryOf(::FirebaseGetAllStudentLessonsUseCase)

    factoryOf(::FirebaseRemoveAllStudentLessonsUseCase)

    factoryOf(::FirebaseRemoveStudentLessonUseCase)

    //tasks

    factoryOf(::FirebaseAddStudentTaskUseCase)

    factoryOf(::FirebaseGetAllStudentTasksUseCase)

    factoryOf(::FirebaseRemoveAllStudentTasksUseCase)

    factoryOf(::FirebaseRemoveStudentTaskUseCase)
}