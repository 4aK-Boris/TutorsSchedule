package dmitriy.losev.core.core

import android.app.Application
import androidx.annotation.StringRes

class ResourcesManager(application: Application) {
    
    private val resources = application.resources
    
    fun getString(@StringRes id: Int): String {
        return resources.getString(id)
    }
}