package dmitriy.losev.database.core.di

import androidx.room.Room
import dmitriy.losev.database.core.DATABASE_NAME
import dmitriy.losev.database.core.TeacherTaskDatabase
import dmitriy.losev.database.data.di.databaseDaoModule
import dmitriy.losev.database.data.di.databaseMapperModule
import dmitriy.losev.database.data.di.databaseRepositoryModule
import dmitriy.losev.database.domain.di.databaseUseCaseModule
import org.koin.dsl.module

val databaseModule = module {

    includes(databaseDaoModule, databaseMapperModule, databaseRepositoryModule, databaseUseCaseModule)

    single {
        Room.databaseBuilder(get(), TeacherTaskDatabase::class.java, DATABASE_NAME).build()
    }
}