package dmitriy.losev.database.data.di

import dmitriy.losev.database.core.TeacherTaskDatabase
import org.koin.dsl.module

val databaseDaoModule = module {

    factory {
        get<TeacherTaskDatabase>().subjectDao()
    }

    factory {
        get<TeacherTaskDatabase>().contactDao()
    }

    factory {
        get<TeacherTaskDatabase>().studentDao()
    }
}