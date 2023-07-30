package dmitriy.losev.tutorsschedule.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.auth.api.identity.Identity
import dmitriy.losev.core.core.BaseActivity
import dmitriy.losev.core.core.BaseViewModel
import dmitriy.losev.tutorsschedule.presentation.ui.MainScreen
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity() {

    private val viewModel by viewModel<MainViewModel>()
    override val baseViewModel: BaseViewModel by lazy { viewModel }

    @Composable
    override fun UI(modifier: Modifier) {

        val client = Identity.getSignInClient(this)

        MainScreen(modifier = modifier, client = client)

    }
}

