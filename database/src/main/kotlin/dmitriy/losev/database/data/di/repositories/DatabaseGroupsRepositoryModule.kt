package dmitriy.losev.database.data.di.repositories

import dmitriy.losev.database.data.repositories.groups.GroupStudentsRepositoryImpl
import dmitriy.losev.database.data.repositories.groups.GroupsRepositoryImpl
import dmitriy.losev.database.domain.repositories.groups.GroupStudentsRepository
import dmitriy.losev.database.domain.repositories.groups.GroupsRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val databaseGroupsRepositoryModule = module {

    factoryOf(::GroupsRepositoryImpl) {
        bind<GroupsRepository>()
    }

    factoryOf(::GroupStudentsRepositoryImpl) {
        bind<GroupStudentsRepository>()
    }
}