package dmitriy.losev.firebase.domain.repositories

interface FirebaseUserDataRepository {

    suspend fun getFirstName(userId: String): String

    suspend fun getLastName(userId: String): String

    suspend fun getPatronymic(userId: String): String

    suspend fun getPhoneNumber(userId: String): String

    suspend fun getFormattedPhoneNumber(userId: String): String

    suspend fun updateFirstName(userId: String, firstName: String)

    suspend fun updateLastName(userId: String, lastName: String)

    suspend fun updatePatronymic(userId: String, patronymic: String)

    suspend fun updatePhoneNumber(userId: String, phoneNumber: String)
}