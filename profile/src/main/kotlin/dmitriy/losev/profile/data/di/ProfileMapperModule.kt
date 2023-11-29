package dmitriy.losev.profile.data.di

import dmitriy.losev.profile.data.mappers.UserAvailabilityMapper
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val profileMapperModule = module {

    factoryOf(::UserAvailabilityMapper)
}