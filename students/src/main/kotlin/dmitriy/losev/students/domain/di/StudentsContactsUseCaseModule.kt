package dmitriy.losev.students.domain.di

import dmitriy.losev.students.domain.usecases.contacts.CallPhoneUseCase
import dmitriy.losev.students.domain.usecases.contacts.ClearPhoneNumberUseCase
import dmitriy.losev.students.domain.usecases.contacts.DiscordUseCase
import dmitriy.losev.students.domain.usecases.contacts.EmailMessageUseCase
import dmitriy.losev.students.domain.usecases.contacts.HasApplicationUseCase
import dmitriy.losev.students.domain.usecases.contacts.PickContactUseCase
import dmitriy.losev.students.domain.usecases.contacts.ReadContactsPermissionUseCase
import dmitriy.losev.students.domain.usecases.contacts.SkypeUseCase
import dmitriy.losev.students.domain.usecases.contacts.SmsUseCase
import dmitriy.losev.students.domain.usecases.contacts.TelegramUseCase
import dmitriy.losev.students.domain.usecases.contacts.ViberAppUseCase
import dmitriy.losev.students.domain.usecases.contacts.WhatsAppUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val studentsContactsUseCaseModule = module {

    factoryOf(::ReadContactsPermissionUseCase)

    factoryOf(::PickContactUseCase)

    factoryOf(::EmailMessageUseCase)

    factoryOf(::CallPhoneUseCase)

    factoryOf(::SmsUseCase)

    factoryOf(::TelegramUseCase)

    factoryOf(::WhatsAppUseCase)

    factoryOf(::ViberAppUseCase)

    factoryOf(::SkypeUseCase)

    factoryOf(::HasApplicationUseCase)

    factoryOf(::ClearPhoneNumberUseCase)

    factoryOf(::DiscordUseCase)
}


