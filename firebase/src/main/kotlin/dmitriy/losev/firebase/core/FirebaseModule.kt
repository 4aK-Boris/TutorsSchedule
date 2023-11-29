package dmitriy.losev.firebase.core

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dmitriy.losev.firebase.data.di.firebaseMapperModule
import dmitriy.losev.firebase.data.di.firebaseRepositoryModule
import dmitriy.losev.firebase.domain.di.firebaseUseCaseModule
import org.koin.dsl.module

val firebaseModule = module {

    single {
        Firebase.auth
    }

    single {
        Firebase.storage
    }

    single {
        Firebase.database("https://tutorsschedule-2305f-default-rtdb.europe-west1.firebasedatabase.app/")
    }

    single {
        get<FirebaseDatabase>().reference
    }

    single {
        get<FirebaseStorage>().reference
    }

    includes(firebaseUseCaseModule, firebaseMapperModule, firebaseRepositoryModule)
}