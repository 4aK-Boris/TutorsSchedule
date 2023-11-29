package dmitriy.losev.tutorsschedule.presentation.di

import dmitriy.losev.tutorsschedule.core.MainViewModel
import dmitriy.losev.tutorsschedule.presentation.viewmodels.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {

    viewModelOf(::MainViewModel)

    viewModelOf(::MainScreenViewModel)
}
