package dmitriy.losev.students.domain.usecases.contacts

import android.app.Application
import android.content.Intent
import android.net.Uri
import dmitriy.losev.students.core.StudentsBaseUseCase
import dmitriy.losev.students.core.exception.EmptySkypeException
import dmitriy.losev.students.core.exception.SkypeApplicationIsNotInstalledException


class SkypeUseCase(
    private val application: Application,
    private val hasApplicationUseCase: HasApplicationUseCase
) : StudentsBaseUseCase() {

    fun openApplication(skype: String) {
        if (skype.isBlank()) throw EmptySkypeException()
        if (hasApplication().not()) throw SkypeApplicationIsNotInstalledException()
        openApplicationWithIntent(skype)
    }

    private fun openApplicationWithIntent(skype: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("skype:$skype?chat")
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        application.startActivity(intent)
    }

    private fun hasApplication(): Boolean {
        return hasApplicationUseCase.hasApplication(PACKAGE)
    }

    companion object {
        private const val PACKAGE = "com.skype.raider"
    }
}