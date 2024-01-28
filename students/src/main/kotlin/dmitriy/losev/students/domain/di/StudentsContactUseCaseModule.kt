package dmitriy.losev.students.domain.di

import dmitriy.losev.students.domain.usecases.contact.AddContactUseCase
import dmitriy.losev.students.domain.usecases.contact.DeleteContactUseCase
import dmitriy.losev.students.domain.usecases.contact.GetContactUseCase
import dmitriy.losev.students.domain.usecases.contact.GetContactsUseCase
import dmitriy.losev.students.domain.usecases.contact.UpdateContactUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val studentsContactUseCaseModule = module {

    factoryOf(::AddContactUseCase)

    factoryOf(::UpdateContactUseCase)

    factoryOf(::DeleteContactUseCase)

    factoryOf(::GetContactUseCase)

    factoryOf(::GetContactsUseCase)
}


