package dmitriy.losev.yandex.presentation.di

import dmitriy.losev.yandex.presentation.viewmodels.YandexAuthenticationViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val yandexViewModelModule = module {

    viewModelOf(::YandexAuthenticationViewModel)
}