package dmitriy.losev.tutorsschedule.domain.di

import dmitriy.losev.tutorsschedule.domain.usecases.FirebaseUseCases
import dmitriy.losev.tutorsschedule.domain.usecases.NavigationUseCases
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {

    factoryOf(::FirebaseUseCases)

    factoryOf(::NavigationUseCases)

//    factoryOf(::AuthenticationNavigationUseCasesImpl) {
//        bind<AuthenticationNavigationUseCases>()
//    }
}