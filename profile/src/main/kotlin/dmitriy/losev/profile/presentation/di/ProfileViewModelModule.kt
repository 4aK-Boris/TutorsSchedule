package dmitriy.losev.profile.presentation.di

import dmitriy.losev.profile.presentation.viewmodels.ChangePasswordScreenViewModel
import dmitriy.losev.profile.presentation.viewmodels.EditProfileScreenViewModel
import dmitriy.losev.profile.presentation.viewmodels.ProfileScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal val profileViewModelModule = module {

    viewModelOf(::ProfileScreenViewModel)

    viewModelOf(::EditProfileScreenViewModel)

    viewModelOf(::ChangePasswordScreenViewModel)
}