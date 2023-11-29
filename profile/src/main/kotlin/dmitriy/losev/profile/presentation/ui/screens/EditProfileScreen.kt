package dmitriy.losev.profile.presentation.ui.screens

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dmitriy.losev.core.presentation.ui.tutorsScheduleBackground
import dmitriy.losev.core.presentation.ui.views.DefaultButton
import dmitriy.losev.profile.R
import dmitriy.losev.profile.core.ProfileNavigationListener
import dmitriy.losev.profile.presentation.ui.views.CanselDialog
import dmitriy.losev.profile.presentation.ui.views.DeleteAccountDialog
import dmitriy.losev.profile.presentation.ui.views.EditIconView
import dmitriy.losev.profile.presentation.ui.views.EditProfileTopBar
import dmitriy.losev.profile.presentation.ui.views.EmailConfirmDialog
import dmitriy.losev.profile.presentation.ui.views.EmailTextFieldView
import dmitriy.losev.profile.presentation.ui.views.ProfileFloatingButton
import dmitriy.losev.profile.presentation.ui.views.ProfileTextFieldView
import dmitriy.losev.profile.presentation.viewmodels.EditProfileScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun EditProfileScreen(
    profileNavigationListener: ProfileNavigationListener,
    viewModel: EditProfileScreenViewModel = koinViewModel()
) {

    val okButtonState by viewModel.buttonState.collectAsState()
    val buttonColor by animateColorAsState(
        targetValue = okButtonState.color,
        animationSpec = spring(),
        label = "okButtonColor"
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            EditProfileTopBar(viewModel, profileNavigationListener)
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            ProfileFloatingButton(buttonColor = buttonColor) {
                viewModel.saveChanges(profileNavigationListener)
            }
        }
    ) { paddingValues ->
        EditProfileScreenBody(
            modifier = Modifier.padding(paddingValues = paddingValues),
            profileNavigationListener = profileNavigationListener,
            viewModel = viewModel
        )
    }
}

@Composable
private fun EditProfileScreenBody(
    modifier: Modifier,
    profileNavigationListener: ProfileNavigationListener,
    viewModel: EditProfileScreenViewModel
) {
    val firstName by viewModel.firstName.collectAsState()
    val lastName by viewModel.lastName.collectAsState()
    val email by viewModel.email.collectAsState()

    val hasNeedEmailVerified by viewModel.hasNeedEmailVerified.collectAsState(initial = true)
    val changePassword by viewModel.changePassword.collectAsState(initial = false)

    val isFirstNameChanged by viewModel.isFirstNameChanged.collectAsState(initial = false)
    val isLastNameChanged by viewModel.isLastNameChanged.collectAsState(initial = false)
    val isEmailChanged by viewModel.isEmailChanged.collectAsState(initial = false)

    val firstNameButtonState by viewModel.firstNameButtonState.collectAsState()
    val lastNameButtonState by viewModel.lastNameButtonState.collectAsState()
    val emailButtonState by viewModel.emailButtonState.collectAsState()

    val emailTimer by viewModel.emailTimer.collectAsState()
    val hasDelayExpired by viewModel.hasDelayExpired.collectAsState(initial = false)
    val emailConfirmDialogState by viewModel.emailConfirmDialogState.collectAsState()
    val deleteAccountDialogState by viewModel.deleteAccountDialogState.collectAsState()
    val cancelDialogState by viewModel.cancelDialogState.collectAsState()

    val backPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    val backCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            viewModel.cansel(profileNavigationListener)
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.loadUserData(profileNavigationListener)
    }

    DisposableEffect(key1 = Unit) {
        backPressedDispatcher?.addCallback(backCallback)

        onDispose {
            backCallback.remove()
        }
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .tutorsScheduleBackground()
            .verticalScroll(state = scrollState),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(align = Alignment.Top),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(height = 32.dp))

            EditIconView(viewModel = viewModel)

            Spacer(modifier = Modifier.height(height = 32.dp))

            ProfileTextFieldView(
                value = firstName,
                onValueChanged = viewModel::onFirstNameChanged,
                isValueChanged = isFirstNameChanged,
                icon = Icons.Default.Person,
                contentDescription = stringResource(id = R.string.first_name),
                buttonState = firstNameButtonState,
                onClick = viewModel::updateFirstName
            )

            Spacer(modifier = Modifier.height(height = 16.dp))

            ProfileTextFieldView(
                value = lastName,
                onValueChanged = viewModel::onLastNameChanged,
                isValueChanged = isLastNameChanged,
                icon = Icons.Default.Person,
                contentDescription = stringResource(id = R.string.last_name),
                buttonState = lastNameButtonState,
                onClick = viewModel::updateLastName
            )

            Spacer(modifier = Modifier.height(height = 16.dp))

            EmailTextFieldView(
                email = email,
                onEmailChanged = viewModel::onEmailChanged,
                isEmailChanged = isEmailChanged,
                buttonState = emailButtonState,
                hasNeedEmailVerified = hasNeedEmailVerified,
                onNotVerifiedEmailClick = viewModel::openEmailConfirmDialog,
                onClick = viewModel::updateEmail
            )

            Spacer(modifier = Modifier.height(height = 16.dp))

            if (changePassword) {
                DefaultButton(
                    text = stringResource(id = R.string.change_password),
                    onClick = {
                        viewModel.navigateToChangePasswordScreen(profileNavigationListener)
                    }
                )
            }

            Spacer(modifier = Modifier.height(height = 16.dp))
        }

        Box(
            modifier = Modifier.padding(bottom = 32.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            DefaultButton(
                text = stringResource(id = R.string.delete_account),
                onClick = viewModel::openDeleteAccountDialog
            )
        }
    }

    EmailConfirmDialog(
        state = emailConfirmDialogState,
        text = stringResource(id = R.string.email_confirm_dialog_text),
        emailTimer = emailTimer,
        hasDelayExpired = hasDelayExpired,
        close = viewModel::closeEmailConfirmDialog,
        onClick = viewModel::sendEmailVerification
    )

    DeleteAccountDialog(
        state = deleteAccountDialogState,
        close = viewModel::closeDeleteAccountDialog
    ) {
        viewModel.deleteAccount(profileNavigationListener)
    }

    CanselDialog(
        state = cancelDialogState,
        close = viewModel::closeCanselDialog,
        noSave = {
            viewModel.noSaveChanges(profileNavigationListener)
        }
    ) {
        viewModel.saveChanges(profileNavigationListener)
    }
}
