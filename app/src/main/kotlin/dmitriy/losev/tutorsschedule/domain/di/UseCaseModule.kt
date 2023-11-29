package dmitriy.losev.tutorsschedule.domain.di

import dmitriy.losev.tutorsschedule.domain.usecases.NavigationAuthenticationUseCases
import dmitriy.losev.tutorsschedule.domain.usecases.NavigationIconUseCases
import dmitriy.losev.tutorsschedule.domain.usecases.NavigationListenerUseCases
import dmitriy.losev.tutorsschedule.domain.usecases.NavigationProfileUseCases
import dmitriy.losev.tutorsschedule.domain.usecases.NavigationStudentsUseCases
import dmitriy.losev.tutorsschedule.domain.usecases.NavigationUseCases
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {

    factoryOf(::NavigationUseCases)

    factoryOf(::NavigationProfileUseCases)

    factoryOf(::NavigationAuthenticationUseCases)

    factoryOf(::NavigationListenerUseCases)

    factoryOf(::NavigationIconUseCases)

    factoryOf(::NavigationStudentsUseCases)
}