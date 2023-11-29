package dmitriy.losev.profile.presentation.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import dmitriy.losev.core.presentation.ui.tutorsScheduleBackground
import dmitriy.losev.core.presentation.ui.views.DefaultPasswordTextField
import dmitriy.losev.core.theme.TutorsScheduleTheme
import dmitriy.losev.profile.R
import dmitriy.losev.profile.core.ProfileNavigationListener
import dmitriy.losev.profile.presentation.ui.views.ChangePasswordTopBar
import dmitriy.losev.profile.presentation.ui.views.ProfileFloatingButton
import dmitriy.losev.profile.presentation.viewmodels.ChangePasswordScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChangePasswordScreen(
    profileNavigationListener: ProfileNavigationListener,
    viewModel: ChangePasswordScreenViewModel = koinViewModel()
) {

    val buttonState by viewModel.buttonBackgroundState.collectAsState()
    val buttonColor by animateColorAsState(
        targetValue = buttonState.color,
        animationSpec = spring(),
        label = "okButtonColor"
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ChangePasswordTopBar {
                viewModel.navigateToEditProfileScreen(profileNavigationListener)
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            ProfileFloatingButton(buttonColor = buttonColor) {
                viewModel.changePassword(profileNavigationListener)
            }
        },
        containerColor = TutorsScheduleTheme.colors.backgroundPrimary,
        contentColor = Color(0xFF8e82d9)

    ) { paddingValues ->
        ChangePasswordScreenBody(
            modifier = Modifier.padding(paddingValues = paddingValues),
            viewModel = viewModel
        )
    }
}

@Composable
private fun ChangePasswordScreenBody(
    modifier: Modifier,
    viewModel: ChangePasswordScreenViewModel
) {

    val password1 by viewModel.password1.collectAsState()
    val password2 by viewModel.password2.collectAsState()

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
            .tutorsScheduleBackground(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(height = 32.dp))

        Image(
            painter = painterResource(id = R.drawable.main_image),
            contentDescription = stringResource(id = R.string.main_image),
            modifier = Modifier.size(size = 256.dp)
        )

        Spacer(modifier = Modifier.height(height = 32.dp))

        DefaultPasswordTextField(
            password = password1,
            imeAction = ImeAction.Next,
            onPasswordChanged = viewModel::opPassword1Changed,
            placeHolder = stringResource(id = R.string.new_passord)
        )

        Spacer(modifier = Modifier.height(height = 16.dp))

        DefaultPasswordTextField(
            password = password2,
            onPasswordChanged = viewModel::opPassword2Changed,
            placeHolder = stringResource(id = R.string.repeat_new_passord)
        )

        Spacer(modifier = Modifier.height(height = 96.dp))
    }

}