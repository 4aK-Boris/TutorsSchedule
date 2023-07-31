package dmitriy.losev.yandex.core

import com.yandex.authsdk.YandexAuthOptions
import com.yandex.authsdk.YandexAuthSdk
import dmitriy.losev.yandex.data.di.yandexMapperModule
import dmitriy.losev.yandex.data.di.yandexRepositoryModule
import dmitriy.losev.yandex.domain.di.yandexUseCaseModule
import org.koin.dsl.module

val yandexModule = module {

    includes(yandexUseCaseModule, yandexMapperModule, yandexRepositoryModule)

    single {
        YandexAuthSdk(get(), YandexAuthOptions(context = get(), loggingEnabled = true))
    }
}