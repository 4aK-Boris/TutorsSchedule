package dmitriy.losev.profile.core

import dmitriy.losev.profile.data.di.profileMapperModule
import dmitriy.losev.profile.data.di.profileNetworkModule
import dmitriy.losev.profile.data.di.profileRepositoryModule
import dmitriy.losev.profile.domain.di.profileUseCaseModule
import dmitriy.losev.profile.presentation.di.profileViewModelModule
import org.koin.dsl.module

val profileModule = module {

    includes(
        profileUseCaseModule,
        profileViewModelModule,
        profileRepositoryModule,
        profileNetworkModule,
        profileMapperModule
    )
}