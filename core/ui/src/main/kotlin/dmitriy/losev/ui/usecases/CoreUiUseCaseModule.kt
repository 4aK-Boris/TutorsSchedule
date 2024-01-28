package dmitriy.losev.ui.usecases

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val coreUiUseCaseModule = module {

    factoryOf(::GetThemeStateUseCase)
}