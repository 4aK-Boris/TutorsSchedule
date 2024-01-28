package dmitriy.losev.students.domain.usecases.contacts

import android.app.Application
import android.content.Intent
import android.net.Uri
import dmitriy.losev.students.core.StudentsBaseUseCase
import dmitriy.losev.students.core.exception.TelegramApplicationIsNotInstalledException
import dmitriy.losev.students.domain.usecases.StudentsCheckPhoneNumberUseCase


class TelegramUseCase(
    private val application: Application,
    private val hasApplicationUseCase: HasApplicationUseCase,
    private val studentsCheckPhoneNumberUseCase: StudentsCheckPhoneNumberUseCase
) : StudentsBaseUseCase() {

    suspend fun openApplication(phoneNumber: String) {
        studentsCheckPhoneNumberUseCase.checkPhoneNumberForEmpty(phoneNumber)
        if (hasApplication().not()) throw TelegramApplicationIsNotInstalledException()
        openApplicationWithIntent(phoneNumber = "+7$phoneNumber")
    }

    private fun openApplicationWithIntent(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("tg://resolve?phone=$phoneNumber")
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        application.startActivity(intent)
    }

    private fun hasApplication(): Boolean {
        return hasApplicationUseCase.hasApplication(PACKAGE)
    }

    companion object {
        private const val PACKAGE = "org.telegram.messenger"
    }
}