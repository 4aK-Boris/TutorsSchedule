package dmitriy.losev.vk.presentation.di

import dmitriy.losev.vk.presentation.viewmodels.VkAuthenticationViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val vkViewModelModule = module {

    viewModelOf(::VkAuthenticationViewModel)
}