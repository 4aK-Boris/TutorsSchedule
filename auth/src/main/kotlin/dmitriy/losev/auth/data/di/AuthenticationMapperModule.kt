package dmitriy.losev.auth.data.di

import dmitriy.losev.auth.data.mappers.UserAbsenceMapper
import dmitriy.losev.auth.data.mappers.UserAvailableMapper
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val authenticationMapperModule = module {

    factoryOf(::UserAvailableMapper)

    factoryOf(::UserAbsenceMapper)
}