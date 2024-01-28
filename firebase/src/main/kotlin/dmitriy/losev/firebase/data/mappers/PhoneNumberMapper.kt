package dmitriy.losev.firebase.data.mappers

class PhoneNumberMapper {

    fun map(phoneNumber: String): String {
        return if (phoneNumber.isNotBlank()) {
            "+7 (${phoneNumber.substring(0, 3)}) ${phoneNumber.substring(3, 6)} - ${phoneNumber.substring(6, 8)} - ${phoneNumber.substring(8, 10)}"
        } else {
            phoneNumber
        }
    }
}