package dmitriy.losev.ui.core.di

import dmitriy.losev.ui.usecases.coreUiUseCaseModule
import dmitriy.losev.ui.views.menu.MenuStateHandler
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreUiModule = module {

    includes(coreUiUseCaseModule)

    singleOf(::MenuStateHandler)
}