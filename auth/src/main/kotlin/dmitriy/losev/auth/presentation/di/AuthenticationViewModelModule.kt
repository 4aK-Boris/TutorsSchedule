package dmitriy.losev.auth.presentation.di

import dmitriy.losev.auth.presentation.viewmodels.DataScreenViewModel
import dmitriy.losev.auth.presentation.viewmodels.EmailLoginScreenViewModel
import dmitriy.losev.auth.presentation.viewmodels.PasswordResetScreenViewModel
import dmitriy.losev.auth.presentation.viewmodels.PasswordScreenViewModel
import dmitriy.losev.auth.presentation.viewmodels.StartScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authenticationViewModelModule = module {

    viewModelOf(::StartScreenViewModel)

    viewModelOf(::EmailLoginScreenViewModel)

    viewModelOf(::DataScreenViewModel)

    viewModelOf(::PasswordResetScreenViewModel)

    viewModelOf(::PasswordScreenViewModel)
}