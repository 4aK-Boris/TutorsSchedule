package dmitriy.losev.firebase.core

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import dmitriy.losev.core.core.ResourcesManager
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object AppInitialize {

    private const val IP = "10.0.2.2"
    private const val AUTH_PORT = 9099
    private const val STORAGE_PORT = 9199
    private const val DATABASE_PORT = 8081

    private val firebaseOptions = FirebaseOptions.Builder()
        .setProjectId(FIREBASE_PROJECT_ID)
        .setApplicationId(FIREBASE_APPLICATION_ID)
        .setStorageBucket(FIREBASE_STORAGE_BUCKET)
        .setDatabaseUrl(FIREBASE_DATABASE)
        .setApiKey(API_KEY)
        .build()

    private var isAppInitialize = false

    fun initialize(context: Context) {
        if (!isAppInitialize) {
            FirebaseApp.initializeApp(context, firebaseOptions)
            useEmulatorInAuth()
            useEmulatorInStorage()
            useEmulatorInDatabase()
        }
        isAppInitialize = true
    }

    fun deleteInitialize() {
        if (isAppInitialize) {
            FirebaseApp.getInstance().delete()
            isAppInitialize = false
        }
    }

    fun testModule(context: Context): Module {
        return module {

            factory {
                context
            }

            singleOf(::ResourcesManager)
        }
    }

    private fun useEmulatorInAuth() {
        FirebaseAuth.getInstance().useEmulator(IP, AUTH_PORT)
    }

    private fun useEmulatorInStorage() {
        FirebaseStorage.getInstance().useEmulator(IP, STORAGE_PORT)
    }

    private fun useEmulatorInDatabase() {
        FirebaseDatabase.getInstance().useEmulator(IP, DATABASE_PORT)
    }
}