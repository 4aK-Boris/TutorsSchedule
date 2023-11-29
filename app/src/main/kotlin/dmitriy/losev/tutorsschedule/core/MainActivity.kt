package dmitriy.losev.tutorsschedule.core

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.auth.api.identity.Identity
import com.vk.auth.api.models.AuthResult
import com.vk.auth.main.VkClientAuthCallback
import com.vk.auth.main.VkClientAuthLib
import dmitriy.losev.core.core.BaseActivity
import dmitriy.losev.core.core.BaseViewModel
import dmitriy.losev.tutorsschedule.presentation.ui.MainScreen
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity() {

    private val viewModel by viewModel<MainViewModel>()
    override val baseViewModel: BaseViewModel by lazy { viewModel }

    private val vkAuthCallback = object : VkClientAuthCallback {
        override fun onAuth(authResult: AuthResult) {
            println("dwadwa")
        }
    }

    @Composable
    override fun UI(modifier: Modifier) {

        val client = Identity.getSignInClient(this)

        MainScreen(modifier = modifier, client = client)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        VkClientAuthLib.addAuthCallback(vkAuthCallback)
    }

    override fun onDestroy() {
        VkClientAuthLib.removeAuthCallback(vkAuthCallback)

        super.onDestroy()
    }

}

