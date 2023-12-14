package dmitriy.losev.firebase.domain.repositories

interface FirebaseNameRepository {

    suspend fun getFirstName(displayName: String?): String?

    suspend fun getLastName(displayName: String?): String?
}