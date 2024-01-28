package dmitriy.losev.students.data.di

import dmitriy.losev.students.data.repository.EmailRepositoryImpl
import dmitriy.losev.students.data.repository.NameRepositoryImpl
import dmitriy.losev.students.data.repository.PhoneNumberRepositoryImpl
import dmitriy.losev.students.domain.repository.EmailRepository
import dmitriy.losev.students.domain.repository.NameRepository
import dmitriy.losev.students.domain.repository.PhoneNumberRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val studentsRepositoryModule = module {

    factoryOf(::PhoneNumberRepositoryImpl) {
        bind<PhoneNumberRepository>()
    }

    factoryOf(::EmailRepositoryImpl) {
        bind<EmailRepository>()
    }

    factoryOf(::NameRepositoryImpl) {
        bind<NameRepository>()
    }
}