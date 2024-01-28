package dmitriy.losev.students.domain.usecases.contacts

import android.app.Application
import android.content.Intent
import android.net.Uri
import dmitriy.losev.students.core.StudentsBaseUseCase


class DiscordUseCase(private val application: Application) : StudentsBaseUseCase() {

    fun openApplication(discord: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("https://discordapp.com/channels/@me/$discord")
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        application.startActivity(intent)
    }
}