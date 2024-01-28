package dmitriy.losev.firebase.data.repositories

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.EMPTY_STRING
import dmitriy.losev.firebase.core.FIRST_NAME
import dmitriy.losev.firebase.core.LAST_NAME
import dmitriy.losev.firebase.core.PATRONYMIC
import dmitriy.losev.firebase.core.PHONE_NUMBER
import dmitriy.losev.firebase.data.mappers.PhoneNumberMapper
import dmitriy.losev.firebase.domain.repositories.FirebaseUserDataRepository
import kotlinx.coroutines.tasks.await

class FirebaseUserDataRepositoryImpl(
    private val reference: DatabaseReference,
    private val phoneNumberMapper: PhoneNumberMapper
): FirebaseUserDataRepository {

    override suspend fun getFirstName(userId: String): String {
        return reference.child(userId).child(FIRST_NAME).get().await().getValue(String::class.java) ?: EMPTY_STRING
    }

    override suspend fun getLastName(userId: String): String {
        return reference.child(userId).child(LAST_NAME).get().await().getValue(String::class.java) ?: EMPTY_STRING
    }

    override suspend fun getPatronymic(userId: String): String {
        return reference.child(userId).child(PATRONYMIC).get().await().getValue(String::class.java) ?: EMPTY_STRING
    }

    override suspend fun getPhoneNumber(userId: String): String {
        return reference.child(userId).child(PHONE_NUMBER).get().await().getValue(String::class.java) ?: EMPTY_STRING
    }

    override suspend fun getFormattedPhoneNumber(userId: String): String {
        return phoneNumberMapper.map(phoneNumber = getPhoneNumber(userId))
    }

    override suspend fun updateFirstName(userId: String, firstName: String) {
        reference.child(userId).child(FIRST_NAME).setValue(firstName)
    }

    override suspend fun updateLastName(userId: String, lastName: String) {
        reference.child(userId).child(LAST_NAME).setValue(lastName)
    }

    override suspend fun updatePatronymic(userId: String, patronymic: String) {
        reference.child(userId).child(PATRONYMIC).setValue(patronymic)
    }

    override suspend fun updatePhoneNumber(userId: String, phoneNumber: String) {
        reference.child(userId).child(PHONE_NUMBER).setValue(phoneNumber)
    }
}