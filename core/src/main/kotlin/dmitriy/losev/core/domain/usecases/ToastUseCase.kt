package dmitriy.losev.core.domain.usecases

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import dmitriy.losev.core.core.ResourcesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ToastUseCase(
    private val context: Context,
    private val resourcesManager: ResourcesManager
) {

    suspend fun showToast(@StringRes id: Int) = withContext(Dispatchers.Main){
        Toast.makeText(context, resourcesManager.getString(id), Toast.LENGTH_SHORT).show()
    }

    suspend fun showToast(text: String)  = withContext(Dispatchers.Main){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}