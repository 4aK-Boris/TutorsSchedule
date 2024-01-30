package dmitriy.losev.firebase.core.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dmitriy.losev.firebase.core.FIREBASE_DATABASE
import dmitriy.losev.firebase.core.FIREBASE_STORAGE
import org.koin.dsl.module

val firebaseCoreModule = module {

    single(createdAtStart = true) {
        Firebase.auth
    }

    single(createdAtStart = true)  {
        Firebase.storage(url = FIREBASE_STORAGE)
    }

    single(createdAtStart = true)  {
        Firebase.database(url = FIREBASE_DATABASE)
    }

    single  {
        get<FirebaseDatabase>().reference
    }

    single  {
        get<FirebaseStorage>().reference
    }
}