package dmitriy.losev.tutorsschedule.core

import dmitriy.losev.auth.core.authenticationModule
import dmitriy.losev.core.di.coreModule
import dmitriy.losev.database.core.di.databaseModule
import dmitriy.losev.datastore.core.dataStoreModule
import dmitriy.losev.firebase.core.di.firebaseModule
import dmitriy.losev.network.networkModule
import dmitriy.losev.profile.core.profileModule
import dmitriy.losev.students.core.studentModule
import dmitriy.losev.subjects.core.subjectsModule
import dmitriy.losev.tutorsschedule.domain.di.useCaseModule
import dmitriy.losev.tutorsschedule.presentation.di.viewModelModule
import dmitriy.losev.ui.core.di.coreUiModule
import org.koin.dsl.module


val appModule = module {

    includes(
        coreModule,
        coreUiModule,
        dataStoreModule,
        viewModelModule,
        authenticationModule,
        firebaseModule,
        useCaseModule,
        networkModule,
        profileModule,
        subjectsModule,
        studentModule,
        databaseModule
    )
}
