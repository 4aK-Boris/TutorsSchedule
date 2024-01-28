package dmitriy.losev.firebase.core

import androidx.test.platform.app.InstrumentationRegistry
import com.google.firebase.auth.FirebaseAuth
import dmitriy.losev.firebase.core.di.firebaseModule
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

abstract class BaseUseCaseTest: KoinTest {

    open suspend fun setUp() { }

    open suspend fun tearDown() { }

    private val context by lazy { InstrumentationRegistry.getInstrumentation().targetContext }

    protected val auth by inject<FirebaseAuth>()

    @get:Rule
    val koinTestRule = KoinTestRule.create {

        val testModule = AppInitialize.testModule(context)

        modules(firebaseModule, testModule)
    }

    @Before
    fun before() = runBlocking {
        AppInitialize.initialize(context)
        setUp()
    }

    @After
    fun after() = runBlocking {
        tearDown()
        AppInitialize.deleteInitialize()
    }

    suspend fun logIn() {
        auth.signInWithEmailAndPassword(EMAIl, PASSWORD).await()
    }

    fun logOut() {
        auth.signOut()
    }
}