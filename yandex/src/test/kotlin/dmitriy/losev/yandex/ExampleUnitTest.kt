package dmitriy.losev.yandex

import dmitriy.losev.core.core.di.coreModule
import dmitriy.losev.network.networkModule
import dmitriy.losev.yandex.core.yandexModule
import dmitriy.losev.yandex.data.network.YandexNetworkImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest: KoinTest {

    private val yandexNetwork by inject<YandexNetworkImpl>()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(yandexModule, coreModule, networkModule)
    }

    @Test
    fun addition_isCorrect(): Unit = runBlocking {
        val data = yandexNetwork.getUserData(token = TOKEN)
        println(data)
    }

    companion object {
        private const val TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2OTA3NDczODQsImp0aSI6IjE3ZjljOTBjLTJmMTQtMTFlZS04OWMyLTBjNDJhMTBhYjdmOCIsImV4cCI6MTcyMjIxMDUwMywiaXNzIjoibG9naW4ueWFuZGV4LnJ1IiwidWlkIjoxNDU5NDYxMzQyLCJsb2dpbiI6ImRtaXRyaXlsb3Nldnh4eCIsInBzdWlkIjoiMS5BQXBDd1EuM24xNDc3d09iSmVwRUJqX0l2U0dsUS5vNkxpRGNGX042N0tfUzBXVE9yVUJ3IiwibmFtZSI6Ilx1MDQxNFx1MDQzY1x1MDQzOFx1MDQ0Mlx1MDQ0MFx1MDQzOCBcdTA0MWJcdTA0M2VcdTA0NDFcdTA0MzVcdTA0MzIiLCJlbWFpbCI6ImRtaXRyaXlsb3Nldnh4eEB5YW5kZXgucnUiLCJnZW5kZXIiOiJtYWxlIiwiZGlzcGxheV9uYW1lIjoiXHUwNDE0XHUwNDNjXHUwNDM4XHUwNDQyXHUwNDQwXHUwNDM4XHUwNDM5IFx1MDQxYlx1MDQzZVx1MDQ0MVx1MDQzNVx1MDQzMiIsImF2YXRhcl9pZCI6IjAvMC0wIn0.kQfuPavggAjh-Y_sZlxJq-0xXSYSgj0xm0NDcAt1khc"
    }
}