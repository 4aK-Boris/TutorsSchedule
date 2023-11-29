package dmitriy.losev.tutorsschedule.core

import dmitriy.losev.auth.core.authenticationModule
import dmitriy.losev.core.core.di.coreModule
import dmitriy.losev.datastore.core.dataStoreModule
import dmitriy.losev.firebase.core.firebaseModule
import dmitriy.losev.network.networkModule
import dmitriy.losev.profile.core.profileModule
import dmitriy.losev.students.core.studentModule
import dmitriy.losev.tutorsschedule.domain.di.useCaseModule
import dmitriy.losev.tutorsschedule.presentation.di.viewModelModule
import org.koin.dsl.module


val appModule = module {

    includes(
        coreModule,
        dataStoreModule,
        viewModelModule,
        authenticationModule,
        firebaseModule,
        useCaseModule,
        networkModule,
        profileModule,
        studentModule
    )
}
