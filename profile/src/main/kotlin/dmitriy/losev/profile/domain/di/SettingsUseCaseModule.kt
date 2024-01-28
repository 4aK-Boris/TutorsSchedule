package dmitriy.losev.profile.domain.di

import dmitriy.losev.profile.domain.usecases.settings.SettingsGetThemeStateUseCase
import dmitriy.losev.profile.domain.usecases.settings.SettingsSetThemeStateUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val settingsUseCaseModule = module {

    factoryOf(::SettingsSetThemeStateUseCase)

    factoryOf(::SettingsGetThemeStateUseCase)
}