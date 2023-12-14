package dmitriy.losev.firebase.data.di.repositories

import dmitriy.losev.firebase.data.repositories.task.FirebaseTasksRepositoryImpl
import dmitriy.losev.firebase.domain.repositories.tasks.FirebaseTasksRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val firebaseTasksRepositoryModule = module {

    factoryOf(::FirebaseTasksRepositoryImpl) {
        bind<FirebaseTasksRepository>()
    }
}