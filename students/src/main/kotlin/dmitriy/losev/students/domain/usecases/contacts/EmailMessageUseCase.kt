package dmitriy.losev.students.domain.usecases.contacts

import android.app.Application
import android.content.ClipDescription
import android.content.Intent
import dmitriy.losev.core.ResourcesManager
import dmitriy.losev.students.R
import dmitriy.losev.students.core.StudentsBaseUseCase
import dmitriy.losev.students.domain.usecases.StudentsCheckEmailUseCase


class EmailMessageUseCase(
    private val application: Application,
    private val resourcesManager: ResourcesManager,
    private val studentsCheckEmailUseCase: StudentsCheckEmailUseCase
) : StudentsBaseUseCase() {

    suspend fun openApplication(email: String) {
        studentsCheckEmailUseCase.checkEmailForEmpty(email)
        openApplicationWithIntent(email)
    }

    private fun openApplicationWithIntent(email: String) {
        with(application) {
            val emailIntent = Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                type = ClipDescription.MIMETYPE_TEXT_PLAIN
            }
            val intent = Intent.createChooser(emailIntent, resourcesManager.getString(id = R.string.choose_email_client)).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
        }
    }
}