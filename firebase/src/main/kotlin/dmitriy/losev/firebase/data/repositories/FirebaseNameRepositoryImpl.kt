package dmitriy.losev.firebase.data.repositories

import dmitriy.losev.firebase.domain.repositories.FirebaseNameRepository

class FirebaseNameRepositoryImpl: FirebaseNameRepository {

    override suspend fun getFirstName(displayName: String?): String? {
        return if (displayName != null && displayName[0] != SPACE) {
            displayName.substringBefore(delimiter = SPACE)
        } else {
            null
        }
    }

    override suspend fun getLastName(displayName: String?): String? {
        return if (displayName != null && displayName[displayName.lastIndex] != SPACE) {
            displayName.substringAfter(delimiter = SPACE)
        } else {
            null
        }
    }

    companion object {
        private const val SPACE = ' '
    }
}