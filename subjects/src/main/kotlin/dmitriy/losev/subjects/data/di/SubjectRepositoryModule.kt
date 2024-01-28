package dmitriy.losev.subjects.data.di

import dmitriy.losev.subjects.data.repositories.SubjectRepositoryImpl
import dmitriy.losev.subjects.data.repositories.SubjectsNameRepositoryImpl
import dmitriy.losev.subjects.domain.repositories.SubjectRepository
import dmitriy.losev.subjects.domain.repositories.SubjectsNameRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val subjectRepositoryModule = module {

    factoryOf(::SubjectsNameRepositoryImpl) {
        bind<SubjectsNameRepository>()
    }

    factoryOf(::SubjectRepositoryImpl) {
        bind<SubjectRepository>()
    }
}