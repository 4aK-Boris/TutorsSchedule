package dmitriy.losev.students.domain.usecases.contacts

import android.app.Application
import android.content.Intent
import android.net.Uri
import dmitriy.losev.students.core.StudentsBaseUseCase
import dmitriy.losev.students.core.exception.ViberApplicationIsNotInstalledException
import dmitriy.losev.students.domain.usecases.StudentsCheckPhoneNumberUseCase


class WhatsAppUseCase(
    private val application: Application,
    private val hasApplicationUseCase: HasApplicationUseCase,
    private val studentsCheckPhoneNumberUseCase: StudentsCheckPhoneNumberUseCase
) : StudentsBaseUseCase() {

    suspend fun openApplication(phoneNumber: String) {
        studentsCheckPhoneNumberUseCase.checkPhoneNumberForEmpty(phoneNumber)
        if (hasApplication().not()) throw ViberApplicationIsNotInstalledException()
        openApplicationWithIntent(phoneNumber = "+7$phoneNumber")
    }

    private fun openApplicationWithIntent(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("whatsapp://send?phone=$phoneNumber")
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        application.startActivity(intent)
    }

    private fun hasApplication(): Boolean {
        return hasApplicationUseCase.hasApplication(PACKAGE)
    }

    companion object {
        private const val PACKAGE = "com.whatsapp"
    }
}