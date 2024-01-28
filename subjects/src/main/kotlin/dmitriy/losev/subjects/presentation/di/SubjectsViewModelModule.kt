package dmitriy.losev.subjects.presentation.di

import dmitriy.losev.subjects.presentation.viewmodels.AddSubjectScreenViewModel
import dmitriy.losev.subjects.presentation.viewmodels.EditSubjectScreenViewModel
import dmitriy.losev.subjects.presentation.viewmodels.SubjectsScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val subjectsViewModelModule = module {

    viewModelOf(::AddSubjectScreenViewModel)

    viewModelOf(::EditSubjectScreenViewModel)

    viewModelOf(::SubjectsScreenViewModel)
}