package dmitriy.losev.yandex.presentation.ui

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import dmitriy.losev.core.AuthenticationListener
import dmitriy.losev.ui.views.buttons.AuthenticationIconButton
import dmitriy.losev.yandex.R
import dmitriy.losev.yandex.presentation.viewmodels.YandexAuthenticationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun YandexAuthenticationButton(
    modifier: Modifier = Modifier,
    authenticationListener: AuthenticationListener,
    viewModel: YandexAuthenticationViewModel = koinViewModel()
) {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        viewModel.authWithYandexIntent(result, authenticationListener)
    }

    AuthenticationIconButton(modifier = modifier, icon = painterResource(id = R.drawable.yandex), contentDescription = stringResource(id = R.string.yandex_login)) {
        viewModel.authWithYandex(launcher, authenticationListener)
    }
}