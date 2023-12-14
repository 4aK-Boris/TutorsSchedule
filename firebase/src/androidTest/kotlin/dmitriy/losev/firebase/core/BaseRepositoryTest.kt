package dmitriy.losev.firebase.core

import androidx.test.platform.app.InstrumentationRegistry
import com.google.firebase.auth.FirebaseAuth
import dmitriy.losev.firebase.core.di.firebaseModule
import dmitriy.losev.firebase.core.exception.UserNotAuthorizationException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

abstract class BaseRepositoryTest: KoinTest {

    open suspend fun setUp() { }

    open suspend fun tearDown() { }

    private val context by lazy { InstrumentationRegistry.getInstrumentation().targetContext }

    protected val auth by inject<FirebaseAuth>()
    protected val userUID by lazy { auth.currentUser?.uid ?: throw UserNotAuthorizationException() }

    @get:Rule
    val koinTestRule = KoinTestRule.create {

        val testModule = AppInitialize.testModule(context)

        modules(firebaseModule, testModule)
    }

    @Before
    fun before() = runBlocking {
        AppInitialize.initialize(context)
        logIn()
        setUp()
    }

    @After
    fun after() = runBlocking {
        tearDown()
        logOut()
        AppInitialize.deleteInitialize()
    }

    private suspend fun logIn() {
        auth.signInWithEmailAndPassword(EMAIl, PASSWORD).await()
    }

    private fun logOut() {
        auth.signOut()
    }
}