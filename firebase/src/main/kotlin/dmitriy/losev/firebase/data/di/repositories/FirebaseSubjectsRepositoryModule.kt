package dmitriy.losev.firebase.data.di.repositories

import dmitriy.losev.firebase.data.repositories.subjects.FirebaseSubjectsRepositoryImpl
import dmitriy.losev.firebase.domain.repositories.subjects.FirebaseSubjectsRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val firebaseSubjectsRepositoryModule = module {

    factoryOf(::FirebaseSubjectsRepositoryImpl) {
        bind<FirebaseSubjectsRepository>()
    }
}