package dmitriy.losev.firebase.usecases.auth

import androidx.test.ext.junit.runners.AndroidJUnit4
import dmitriy.losev.firebase.core.usecases.BaseAuthUseCaseTest
import dmitriy.losev.firebase.domain.models.FirebaseToken
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseTokenAuthUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class FirebaseTokenAuthUseCaseTest: BaseAuthUseCaseTest() {

    private val firebaseToken = FirebaseToken(TOKEN)

    private val firebaseTokenAuthUseCase by inject<FirebaseTokenAuthUseCase>()

    override suspend fun tearDown() {
        logOut()
    }

    @Test
    fun testAuthWithToken(): Unit = runBlocking {

        firebaseTokenAuthUseCase.authWithToken(firebaseToken)

        assertTrue(isAuth)
    }

    companion object {
        private const val TOKEN = "eyJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJodHRwczovL2lkZW50aXR5dG9vbGtpdC5nb29nbGVhcGlzLmNvbS9nb29nbGUuaWRlbnRpdHkuaWRlbnRpdHl0b29sa2l0LnYxLklkZW50aXR5VG9vbGtpdCIsImV4cCI6MTcwMjM1MzA3MCwiaWF0IjoxNzAyMzQ5NDcwLCJpc3MiOiJmaXJlYmFzZS1hZG1pbnNkay16dGp1eUB0dXRvcnNzY2hlZHVsZS0yMzA1Zi5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsInN1YiI6ImZpcmViYXNlLWFkbWluc2RrLXp0anV5QHR1dG9yc3NjaGVkdWxlLTIzMDVmLmlhbS5nc2VydmljZWFjY291bnQuY29tIiwidWlkIjoiLU5rTG1aTVFSSnVuaGdsQXRXWm0ifQ.Js3uKUZ6n2SMZG10l0qlk7uj2vMXt5WY8bI-U5zkjGyKm4PkZdz4J-7bQlSXB9f4RGc-unWqfz9-SDOzxEhUhPQ7MSD1kk9KtN9OH-Hg1VpbrFyTBPbes_vbvgYsCzHzVCPg84xgJe3rH97N2_ZoSBvWYldvMtsNlMQlSnCAD4-I1h7QuwSl-SUK__xJlg1qdQetkik_EHsz0FnvJmYUI-iHeNJt9dmbT0fE9PwEomJoHqRc54ZlnUQHP8Cp4a-oHmW7vi8VLR_sj2NMSRbAvFcsX2LL42fgl8rKGGhkw0rxEef1J5udB4buUs-aSMfgQ-rQ__QgtfjuNDzzKJugug"
    }
}