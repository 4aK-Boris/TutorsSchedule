package dmitriy.losev.profile.data.di

import dmitriy.losev.profile.data.mappers.FullUserDataMapper
import dmitriy.losev.profile.data.mappers.UriMapper
import dmitriy.losev.profile.data.mappers.UserAbsenceMapper
import dmitriy.losev.profile.data.mappers.UserAvailableMapper
import dmitriy.losev.profile.data.mappers.UserDataMapper
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val profileMapperModule = module {

    factoryOf(::UserAbsenceMapper)

    factoryOf(::UserAvailableMapper)

    factoryOf(::UriMapper)

    factoryOf(::UserDataMapper)

    factoryOf(::FullUserDataMapper)
}