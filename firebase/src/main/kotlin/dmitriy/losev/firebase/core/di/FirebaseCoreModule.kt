package dmitriy.losev.firebase.core.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import org.koin.dsl.module

val firebaseCoreModule = module {

    single {
        Firebase.auth
    }

    single {
        Firebase.storage
    }

    single {
        Firebase.database
    }

    single {
        get<FirebaseDatabase>().reference
    }

    single {
        get<FirebaseStorage>().reference
    }
}