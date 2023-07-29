package dmitriy.losev.tutorsschedule.core

import dmitriy.losev.core.core.di.coreModule
import dmitriy.losev.firebase.core.firebaseModule
import dmitriy.losev.tutorsschedule.domain.di.useCaseModule
import dmitriy.losev.tutorsschedule.presentation.di.viewModelModule
import org.koin.dsl.module


val appModule = module {

    includes(
        coreModule,
        viewModelModule,
        //authenticationModule,
        firebaseModule,
        useCaseModule
    )
}
