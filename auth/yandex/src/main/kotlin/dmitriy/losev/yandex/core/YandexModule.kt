package dmitriy.losev.yandex.core

import com.yandex.authsdk.YandexAuthOptions
import com.yandex.authsdk.YandexAuthSdk
import dmitriy.losev.yandex.data.di.yandexMapperModule
import dmitriy.losev.yandex.data.di.yandexNetworkModule
import dmitriy.losev.yandex.data.di.yandexRepositoryModule
import dmitriy.losev.yandex.domain.di.yandexUseCaseModule
import dmitriy.losev.yandex.presentation.di.yandexViewModelModule
import org.koin.dsl.module

val yandexModule = module {

    includes(
        yandexUseCaseModule,
        yandexMapperModule,
        yandexRepositoryModule,
        yandexNetworkModule,
        yandexViewModelModule
    )

    single {
        YandexAuthSdk(get(), YandexAuthOptions(context = get(), loggingEnabled = true))
    }
}