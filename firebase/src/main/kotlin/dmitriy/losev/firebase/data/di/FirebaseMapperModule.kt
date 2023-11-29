package dmitriy.losev.firebase.data.di

import dmitriy.losev.firebase.data.mappers.FirebaseTokenMapper
import dmitriy.losev.firebase.data.mappers.StudentMapper
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val firebaseMapperModule = module {

    factoryOf(::FirebaseTokenMapper)

    factoryOf(::StudentMapper)
}