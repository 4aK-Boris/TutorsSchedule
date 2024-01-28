package dmitriy.losev.core

import android.content.Context
import androidx.annotation.StringRes

class ResourcesManager(context: Context) {
    
    private val resources = context.resources
    
    fun getString(@StringRes id: Int): String {
        return resources.getString(id)
    }
}