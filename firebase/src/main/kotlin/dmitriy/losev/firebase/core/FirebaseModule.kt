package dmitriy.losev.firebase.core

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dmitriy.losev.firebase.domain.di.firebaseUseCaseModule
import dmitriy.losev.vk.core.vkModule
import dmitriy.losev.yandex.core.yandexModule
import org.koin.dsl.module

val firebaseModule = module {

    single {
        Firebase.auth
    }

    single {
        Firebase.storage
    }

    single {
        get<FirebaseStorage>().reference
    }

    includes(firebaseUseCaseModule, yandexModule, vkModule)
}