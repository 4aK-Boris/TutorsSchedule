package dmitriy.losev.vk.presentation.ui

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import dmitriy.losev.core.AuthenticationListener
import dmitriy.losev.ui.views.buttons.AuthenticationIconButton
import dmitriy.losev.vk.R
import dmitriy.losev.vk.presentation.viewmodels.VkAuthenticationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun VKAuthenticationButton(
    modifier: Modifier = Modifier,
    authenticationListener: AuthenticationListener,
    viewModel: VkAuthenticationViewModel = koinViewModel()
) {

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            viewModel.authWithVkIntent(result, authenticationListener)
        }

    AuthenticationIconButton(modifier = modifier, icon = painterResource(id = R.drawable.vk), contentDescription = stringResource(id = R.string.vk_login)) {
        viewModel.authWithVk(launcher, authenticationListener)
    }
}