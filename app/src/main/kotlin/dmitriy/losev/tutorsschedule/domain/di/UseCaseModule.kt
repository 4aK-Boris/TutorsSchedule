package dmitriy.losev.tutorsschedule.domain.di

import dmitriy.losev.auth.domain.usecases.AuthenticationNavigationUseCases
import dmitriy.losev.tutorsschedule.domain.usecases.AuthenticationNavigationUseCasesImpl
import dmitriy.losev.tutorsschedule.domain.usecases.FirebaseUseCases
import dmitriy.losev.tutorsschedule.domain.usecases.NavigationUseCases
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {

    factoryOf(::FirebaseUseCases)

    factoryOf(::NavigationUseCases)

    factoryOf(::AuthenticationNavigationUseCasesImpl) {
        bind<AuthenticationNavigationUseCases>()
    }
}