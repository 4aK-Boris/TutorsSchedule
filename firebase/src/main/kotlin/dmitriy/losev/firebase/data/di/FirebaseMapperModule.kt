package dmitriy.losev.firebase.data.di

import dmitriy.losev.firebase.data.mappers.ContactMapper
import dmitriy.losev.firebase.data.mappers.FirebaseTokenMapper
import dmitriy.losev.firebase.data.mappers.GroupMapper
import dmitriy.losev.firebase.data.mappers.LessonMapper
import dmitriy.losev.firebase.data.mappers.NameMapper
import dmitriy.losev.firebase.data.mappers.PeriodMapper
import dmitriy.losev.firebase.data.mappers.PhoneNumberMapper
import dmitriy.losev.firebase.data.mappers.SimpleStudentMapper
import dmitriy.losev.firebase.data.mappers.StudentMapper
import dmitriy.losev.firebase.data.mappers.StudentNameMapper
import dmitriy.losev.firebase.data.mappers.SubjectMapper
import dmitriy.losev.firebase.data.mappers.TaskMapper
import dmitriy.losev.firebase.data.mappers.UriMapper
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val firebaseMapperModule = module {

    factoryOf(::FirebaseTokenMapper)

    factoryOf(::StudentMapper)

    factoryOf(::SimpleStudentMapper)

    factoryOf(::ContactMapper)

    factoryOf(::NameMapper)

    factoryOf(::UriMapper)

    factoryOf(::GroupMapper)

    factoryOf(::SubjectMapper)

    factoryOf(::LessonMapper)

    factoryOf(::TaskMapper)

    factoryOf(::PeriodMapper)

    factoryOf(::PhoneNumberMapper)

    factoryOf(::StudentNameMapper)
}