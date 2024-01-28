package dmitriy.losev.students.domain.usecases.contacts

import android.app.Application
import android.content.Intent
import android.net.Uri
import dmitriy.losev.students.core.StudentsBaseUseCase
import dmitriy.losev.students.domain.usecases.StudentsCheckPhoneNumberUseCase

class CallPhoneUseCase(
    private val application: Application,
    private val studentsCheckPhoneNumberUseCase: StudentsCheckPhoneNumberUseCase
) : StudentsBaseUseCase() {

    suspend fun openApplication(phoneNumber: String) {
        studentsCheckPhoneNumberUseCase.checkPhoneNumberForEmpty(phoneNumber)
        openApplicationWithIntent(phoneNumber)
    }

    private fun openApplicationWithIntent(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel: +7$phoneNumber")
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        application.startActivity(intent)
    }
}