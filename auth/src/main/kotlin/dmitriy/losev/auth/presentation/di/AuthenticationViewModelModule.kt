package dmitriy.losev.auth.presentation.di

import dmitriy.losev.auth.presentation.viewmodels.RegistrationScreenViewModel
import dmitriy.losev.auth.presentation.viewmodels.LoginScreenViewModel
import dmitriy.losev.auth.presentation.viewmodels.PasswordResetScreenViewModel
import dmitriy.losev.auth.presentation.viewmodels.PasswordScreenViewModel
import dmitriy.losev.auth.presentation.viewmodels.StartScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authenticationViewModelModule = module {

    viewModelOf(::StartScreenViewModel)

    viewModelOf(::LoginScreenViewModel)

    viewModelOf(::RegistrationScreenViewModel)

    viewModelOf(::PasswordResetScreenViewModel)

    viewModelOf(::PasswordScreenViewModel)
}