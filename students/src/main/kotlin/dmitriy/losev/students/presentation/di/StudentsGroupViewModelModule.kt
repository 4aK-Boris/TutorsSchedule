package dmitriy.losev.students.presentation.di

import dmitriy.losev.students.presentation.viewmodels.groups.AddGroupScreenViewModel
import dmitriy.losev.students.presentation.viewmodels.groups.ChooseStudentsScreenViewModel
import dmitriy.losev.students.presentation.viewmodels.groups.GroupScreenViewModel
import dmitriy.losev.students.presentation.viewmodels.groups.GroupsScreenViewModel
import dmitriy.losev.students.presentation.viewmodels.groups.UpdateGroupScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val studentsGroupViewModelModule = module {

    viewModelOf(::AddGroupScreenViewModel)

    viewModelOf(::ChooseStudentsScreenViewModel)

    viewModelOf(::GroupsScreenViewModel)

    viewModelOf(::UpdateGroupScreenViewModel)

    viewModelOf(::GroupScreenViewModel)
}