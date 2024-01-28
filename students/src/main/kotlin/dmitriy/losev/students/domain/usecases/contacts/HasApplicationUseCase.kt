package dmitriy.losev.students.domain.usecases.contacts

import android.app.Application
import android.content.pm.PackageManager
import dmitriy.losev.students.core.StudentsBaseUseCase

class HasApplicationUseCase(private val application: Application) : StudentsBaseUseCase() {

    fun hasApplication(`package`: String): Boolean {
        val packageManager = application.packageManager
        return try {
            packageManager.getPackageInfo(`package`, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
}