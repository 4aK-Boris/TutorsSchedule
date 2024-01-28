package dmitriy.losev.firebase.core

import androidx.test.platform.app.InstrumentationRegistry
import dmitriy.losev.core.ResourcesManager
import dmitriy.losev.firebase.core.di.firebaseModule
import org.junit.After
import org.junit.Before
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import kotlin.test.Test

class DiTest : KoinTest {

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        AppInitialize.initialize(context)
    }

    @After
    fun tearDown() {
        AppInitialize.deleteInitialize()
    }

    @Test
    fun verifyKoinApp() {

        val context = InstrumentationRegistry.getInstrumentation().targetContext

        val testModule = module {

            factory {
                context
            }

            singleOf(::ResourcesManager)
        }

        koinApplication {
            modules(firebaseModule, testModule)
            checkModules()
        }
    }
}