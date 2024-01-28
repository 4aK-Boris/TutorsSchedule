package dmitriy.losev.profile.presentation.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dmitriy.losev.profile.R
import dmitriy.losev.profile.core.ProfileNavigationListener
import dmitriy.losev.profile.presentation.ui.views.SettingsSwitch
import dmitriy.losev.profile.presentation.viewmodels.SettingsScreenViewModel
import dmitriy.losev.ui.core.THEME_DEFAULT_VALUE
import dmitriy.losev.ui.views.Title
import dmitriy.losev.ui.views.VerticalSpacer
import dmitriy.losev.ui.views.boxes.ColumnSecondary
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(profileNavigationListener: ProfileNavigationListener, viewModel: SettingsScreenViewModel = koinViewModel()) {

    val themeState by viewModel.themeSettingsState.collectAsState(initial = THEME_DEFAULT_VALUE)

    LaunchedEffect(key1 = Unit) {
        viewModel.loadSettings()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Title(title = stringResource(id = R.string.additional_settings)) {
                viewModel.back(profileNavigationListener)
            }
        }
    ) { paddingValues ->

        ColumnSecondary(modifier = Modifier.padding(paddingValues = paddingValues)) {

            VerticalSpacer(height = 24.dp)

            SettingsSwitch(
                state = themeState,
                onStateChanged = viewModel::onThemeStateChanged,
                title = stringResource(id = R.string.theme_title),
                hint = stringResource(id = R.string.theme_title_hint)
            )

            VerticalSpacer(height = 24.dp)
        }
    }
}