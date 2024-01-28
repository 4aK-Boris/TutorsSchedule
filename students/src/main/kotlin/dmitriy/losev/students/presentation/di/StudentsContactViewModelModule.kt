package dmitriy.losev.students.presentation.di

import dmitriy.losev.students.presentation.viewmodels.contact.AddContactScreenViewModel
import dmitriy.losev.students.presentation.viewmodels.contact.ContactScreenViewModel
import dmitriy.losev.students.presentation.viewmodels.contact.UpdateContactScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val studentsContactViewModelModule = module {

    viewModelOf(::AddContactScreenViewModel)

    viewModelOf(::UpdateContactScreenViewModel)

    viewModelOf(::ContactScreenViewModel)
}