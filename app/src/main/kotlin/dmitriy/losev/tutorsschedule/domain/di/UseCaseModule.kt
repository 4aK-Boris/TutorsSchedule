package dmitriy.losev.tutorsschedule.domain.di

import dmitriy.losev.tutorsschedule.domain.usecases.MainNavigationUseCases
import dmitriy.losev.tutorsschedule.domain.usecases.NavigationAuthenticationUseCases
import dmitriy.losev.tutorsschedule.domain.usecases.NavigationListenerUseCases
import dmitriy.losev.tutorsschedule.domain.usecases.NavigationProfileUseCases
import dmitriy.losev.tutorsschedule.domain.usecases.NavigationStudentsUseCases
import dmitriy.losev.tutorsschedule.domain.usecases.NavigationSubjectsUseCases
import dmitriy.losev.tutorsschedule.domain.usecases.NavigationMainUseCases
import dmitriy.losev.tutorsschedule.domain.usecases.NavigationUseCases
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {

    factoryOf(::NavigationMainUseCases)

    factoryOf(::NavigationProfileUseCases)

    factoryOf(::NavigationAuthenticationUseCases)

    factoryOf(::NavigationListenerUseCases)

    factoryOf(::NavigationSubjectsUseCases)

    //factoryOf(::NavigationIconUseCases)

    factoryOf(::NavigationStudentsUseCases)

    factoryOf(::NavigationUseCases)

    factoryOf(::MainNavigationUseCases)
}