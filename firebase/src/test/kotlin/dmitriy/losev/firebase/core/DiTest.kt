package dmitriy.losev.firebase.core

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dmitriy.losev.core.core.ResourcesManager
import dmitriy.losev.firebase.data.di.firebaseMapperModule
import dmitriy.losev.firebase.data.di.firebaseRepositoryModule
import dmitriy.losev.firebase.domain.di.firebaseUseCaseModule
import io.mockk.mockkClass
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.koin.test.KoinTest
import org.koin.test.check.checkKoinModules
import org.koin.test.mock.MockProvider

class DiTest : KoinTest {

    @get:Rule
    val mockProvider = MockProvider.register { clazz -> mockkClass(type = clazz, relaxed = true) }

    @Test
    fun verifyKoinApp() {

        checkKoinModules(listOf(firebaseUseCaseModule, firebaseMapperModule, firebaseRepositoryModule)) {
            withInstance<FirebaseAuth>()
            withInstance<FirebaseStorage>()
            withInstance<FirebaseDatabase>()
            withInstance<StorageReference>()
            withInstance<DatabaseReference>()
            withInstance<ResourcesManager>()
        }
    }
}