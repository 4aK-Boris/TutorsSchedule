package dmitriy.losev.profile.presentation.ui.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import dmitriy.losev.profile.R
import dmitriy.losev.profile.core.PASSWORD
import dmitriy.losev.profile.core.ProfileNavigationListener
import dmitriy.losev.profile.presentation.ui.views.Block
import dmitriy.losev.profile.presentation.ui.views.BlockWithIcon
import dmitriy.losev.profile.presentation.ui.views.ExitIconButton
import dmitriy.losev.profile.presentation.viewmodels.EditProfileScreenViewModel
import dmitriy.losev.ui.theme.LocalTutorsScheduleShapes
import dmitriy.losev.ui.views.EditableText
import dmitriy.losev.ui.views.HorizontalSpacer
import dmitriy.losev.ui.views.Title
import dmitriy.losev.ui.views.Title2Text
import dmitriy.losev.ui.views.VerticalSpacer
import dmitriy.losev.ui.views.boxes.ColumnSecondaryWithLoader
import dmitriy.losev.ui.views.buttons.CriticalButton
import dmitriy.losev.ui.views.buttons.PrimaryButton
import dmitriy.losev.ui.views.popups.DeleteProfilePopUp
import dmitriy.losev.ui.views.popups.EmailPopUp
import dmitriy.losev.ui.views.popups.ExitPopUp
import dmitriy.losev.ui.views.popups.PhotoPopUp
import dmitriy.losev.ui.views.textfields.DefaultTextField
import dmitriy.losev.ui.views.textfields.PhoneTextField
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun EditProfileScreen(
    profileNavigationListener: ProfileNavigationListener,
    uri: String?,
    viewModel: EditProfileScreenViewModel = koinViewModel()
) {

    val avatarShape = LocalTutorsScheduleShapes.current.avatar

    val avatar by viewModel.avatar.collectAsState(initial = null)
    val displayName by viewModel.displayName.collectAsState()
    val email by viewModel.email.collectAsState()
    val firstName by viewModel.firstName.collectAsState()
    val lastName by viewModel.lastName.collectAsState()
    val patronymic by viewModel.patronymic.collectAsState()
    val phoneNumber by viewModel.phoneNumber.collectAsState()

    val popUpDeleteVisible by viewModel.popUpDeleteVisible.collectAsState()
    val popUpExitVisible by viewModel.popUpExitVisible.collectAsState()
    val popUpEmailVisible by viewModel.popUpEmailVisible.collectAsState()
    val popUpPhotoVisible by viewModel.popUpPhotoVisible.collectAsState()

    val hasEmailChanged by viewModel.hasEmailChanged.collectAsState()
    val isEmailVerified by viewModel.isEmailVerified.collectAsState()
    val hasPasswordChanged by viewModel.hasPasswordChanged.collectAsState()

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    LaunchedEffect(key1 = Unit) {
        viewModel.onAvatarUriChanged(uri)
        viewModel.loadUserData(profileNavigationListener)
    }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { pictureUri ->
        viewModel.onAvatarUriChanged(pictureUri)
    }

    val permissionState = rememberMultiplePermissionsState(
        listOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Title(title = stringResource(id = R.string.settings)) {
                viewModel.back(profileNavigationListener)
            }
        }
    ) { paddingValues ->

        ColumnSecondaryWithLoader(modifier = Modifier.padding(paddingValues = paddingValues), viewModel = viewModel) {

            VerticalSpacer(height = 10.dp)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(avatar)
                        .crossfade(enable = true)
                        .build(),
                    placeholder = painterResource(R.drawable.avatar),
                    error = painterResource(R.drawable.avatar),
                    fallback = painterResource(R.drawable.avatar),
                    contentDescription = stringResource(R.string.avatar),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(size = 96.dp)
                        .clip(avatarShape)
                        .clickable(onClick = viewModel::showPhotoPopUp)
                )

                HorizontalSpacer(width = 8.dp)

                Title2Text(text = displayName, modifier = Modifier.width(width = screenWidth - 176.dp))

                HorizontalSpacer(width = 8.dp)

                ExitIconButton(onClick = viewModel::showExitPopUp)
            }

            VerticalSpacer(height = 24.dp)

            EditableText(
                title = stringResource(id = R.string.email2),
                hint = stringResource(id = R.string.email),
                text = email,
                enabled = hasEmailChanged,
                iconEnabled = hasEmailChanged && isEmailVerified
            ) {
                viewModel.onEmailEditClick(profileNavigationListener)
            }

            VerticalSpacer(height = 24.dp)

            EditableText(
                title = stringResource(id = R.string.password),
                hint = stringResource(id = R.string.password),
                text = PASSWORD,
                enabled = hasPasswordChanged,
                iconEnabled = hasPasswordChanged
            ) {
                viewModel.onPasswordEditClick(profileNavigationListener)
            }

            VerticalSpacer(height = 24.dp)

            PhoneTextField(
                title = stringResource(id = R.string.phone),
                phone = phoneNumber,
                onPhoneChanged = viewModel::onPhoneNumberChanged,
                imeAction = ImeAction.Next
            )

            VerticalSpacer(height = 24.dp)

            DefaultTextField(
                title = stringResource(id = R.string.first_name),
                hint = stringResource(id = R.string.first_name),
                text = firstName,
                onTextChanged = viewModel::onFirstNameChanged,
                imeAction = ImeAction.Next,
                autoCorrect = true,
                capitalization = KeyboardCapitalization.Words
            )

            VerticalSpacer(height = 24.dp)

            DefaultTextField(
                title = stringResource(id = R.string.last_name),
                hint = stringResource(id = R.string.last_name),
                text = lastName,
                onTextChanged = viewModel::onLastNameChanged,
                imeAction = ImeAction.Next,
                autoCorrect = true,
                capitalization = KeyboardCapitalization.Words
            )

            VerticalSpacer(height = 24.dp)

            DefaultTextField(
                title = stringResource(id = R.string.patronymic),
                hint = stringResource(id = R.string.patronymic),
                text = patronymic,
                onTextChanged = viewModel::onPatronymicChanged,
                imeAction = ImeAction.Next,
                autoCorrect = true,
                capitalization = KeyboardCapitalization.Words
            )


            VerticalSpacer(height = 24.dp)

            BlockWithIcon(title = stringResource(id = R.string.settings_title), hint = stringResource(id = R.string.settings_title_hint)) {
                viewModel.navigateToSettingsScreen(profileNavigationListener)
            }

            VerticalSpacer(height = 24.dp)

            Block(title = stringResource(id = R.string.delete_title), hint = stringResource(id = R.string.delete_title_hint))

            VerticalSpacer(height = 12.dp)

            CriticalButton(text = stringResource(id = R.string.delete_profile), onClick = viewModel::showDeletePopUp)

            VerticalSpacer(height = 48.dp)

            PrimaryButton(text = stringResource(id = R.string.save)) {
                viewModel.saveChanges(profileNavigationListener)
            }

            VerticalSpacer(height = 40.dp)
        }
    }

    DeleteProfilePopUp(visible = popUpDeleteVisible, close = viewModel::closeDeletePopUp) {
        viewModel.deleteAccount(profileNavigationListener)
    }

    ExitPopUp(visible = popUpExitVisible, close = viewModel::closeExitPopUp) {
        viewModel.logOut(profileNavigationListener)
    }

    EmailPopUp(visible = popUpEmailVisible, close = viewModel::closeEmailPopUp, sendMail = viewModel::sendMail)

    PhotoPopUp(
        visible = popUpPhotoVisible,
        close = viewModel::closePhotoPopUp,
        chooseFromGallery = { viewModel.pickPhoto(launcher) },
        createPhoto = { viewModel.createPhoto(profileNavigationListener, permissionState) }
    )
}

