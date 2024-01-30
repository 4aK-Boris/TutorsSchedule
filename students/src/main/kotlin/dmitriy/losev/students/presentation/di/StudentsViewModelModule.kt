package dmitriy.losev.students.presentation.di

import dmitriy.losev.students.presentation.viewmodels.MainStudentsScreenViewModel
import dmitriy.losev.students.presentation.viewmodels.students.AddStudentScreenViewModel
import dmitriy.losev.students.presentation.viewmodels.students.StudentScreenViewModel
import dmitriy.losev.students.presentation.viewmodels.students.StudentsScreenViewModel
import dmitriy.losev.students.presentation.viewmodels.students.UpdateStudentScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val studentsViewModelModule = module {

    includes(studentsContactViewModelModule, studentsGroupViewModelModule)

    viewModelOf(::AddStudentScreenViewModel)

    viewModelOf(::StudentsScreenViewModel)

    viewModelOf(::UpdateStudentScreenViewModel)

    viewModelOf(::StudentScreenViewModel)

    viewModelOf(::MainStudentsScreenViewModel)
}