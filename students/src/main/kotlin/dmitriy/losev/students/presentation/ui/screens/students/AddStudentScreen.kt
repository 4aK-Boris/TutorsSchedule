package dmitriy.losev.students.presentation.ui.screens.students

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import dmitriy.losev.students.R
import dmitriy.losev.students.core.StudentsNavigationListener
import dmitriy.losev.students.presentation.viewmodels.students.AddStudentScreenViewModel
import dmitriy.losev.ui.views.TitleWithSaveIcon
import dmitriy.losev.ui.views.VerticalSpacer
import dmitriy.losev.ui.views.boxes.ColumnSecondaryWithLoader
import dmitriy.losev.ui.views.buttons.ContactIconButton
import dmitriy.losev.ui.views.textfields.CommentTextField
import dmitriy.losev.ui.views.textfields.DefaultTextField
import dmitriy.losev.ui.views.textfields.DiscordTextField
import dmitriy.losev.ui.views.textfields.EmailTextFieldWithIcon
import dmitriy.losev.ui.views.textfields.PhoneTextFieldWithIcon
import dmitriy.losev.ui.views.textfields.SkypeTextField
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AddStudentScreen(
    studentsNavigationListener: StudentsNavigationListener, viewModel: AddStudentScreenViewModel = koinViewModel()
) {

    val firstName by viewModel.firstName.collectAsState()
    val lastName by viewModel.lastName.collectAsState()
    val patronymic by viewModel.patronymic.collectAsState()
    val phoneNumber by viewModel.phoneNumber.collectAsState()
    val email by viewModel.email.collectAsState()
    val skype by viewModel.skype.collectAsState()
    val discord by viewModel.discord.collectAsState()
    val comment by viewModel.comment.collectAsState()

    val firstNameTextFieldState by viewModel.firstNameTextFieldState.collectAsState()
    val emailTextFieldState by viewModel.emailTextFieldState.collectAsState()
    val phoneNumberTextFieldState by viewModel.phoneNumberTextFieldState.collectAsState()

    val commentVisible by viewModel.commentVisible.collectAsState()

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        viewModel.pickContactResult(result)
    }

    val phoneNumberPermissionState = rememberPermissionState(
        android.Manifest.permission.READ_CONTACTS
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TitleWithSaveIcon(
                title = stringResource(id = R.string.add_student_screen_title),
                back = { viewModel.back(studentsNavigationListener) },
                save = { viewModel.saveChanges(studentsNavigationListener) }
            )
        }
    ) { paddingValues ->

        ColumnSecondaryWithLoader(modifier = Modifier.padding(paddingValues = paddingValues), viewModel = viewModel) {

            VerticalSpacer(height = 24.dp)

            DefaultTextField(
                title = stringResource(id = R.string.first_name_title),
                hint = stringResource(id = R.string.first_name_title),
                text = firstName,
                onTextChanged = viewModel::onFirstNameChanged,
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next,
                textFieldState = firstNameTextFieldState,
                clearTextFieldState = viewModel::clearFirstNameTextFieldState
            )

            VerticalSpacer(height = 24.dp)

            DefaultTextField(
                title = stringResource(id = R.string.last_name_title),
                hint = stringResource(id = R.string.last_name_title),
                text = lastName,
                onTextChanged = viewModel::onLastNameChanged,
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            )

            VerticalSpacer(height = 24.dp)

            DefaultTextField(
                title = stringResource(id = R.string.patronymic_title),
                hint = stringResource(id = R.string.patronymic_title),
                text = patronymic,
                onTextChanged = viewModel::onPatronymicChanged,
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            )

            VerticalSpacer(height = 24.dp)

            EmailTextFieldWithIcon(
                title = stringResource(id = R.string.email_title),
                email = email,
                onEmailChanged = viewModel::onEmailChanged,
                imeAction = ImeAction.Next,
                textFieldState = emailTextFieldState,
                clearTextFieldState = viewModel::clearEmailTextFieldState
            )

            VerticalSpacer(height = 24.dp)

            PhoneTextFieldWithIcon(
                title = stringResource(id = R.string.phone_number_title),
                phone = phoneNumber,
                onPhoneChanged = viewModel::onPhoneNumberChanged,
                imeAction = ImeAction.Next,
                textFieldState = phoneNumberTextFieldState,
                clearTextFieldState = viewModel::clearPhoneNumberTextFieldState
            ) {
                ContactIconButton {
                    viewModel.pickContactIntent(phoneNumberPermissionState, launcher)
                }
            }

            VerticalSpacer(height = 24.dp)

            SkypeTextField(
                skype = skype,
                imeAction = ImeAction.Next,
                onSkypeChanged = viewModel::onSkypeChanged
            )

            VerticalSpacer(height = 24.dp)

            DiscordTextField(
                discord = discord,
                imeAction = ImeAction.Next,
                onDiscordChanged = viewModel::onDiscordChanged
            )

            VerticalSpacer(height = 24.dp)

            CommentTextField(
                title = stringResource(id = R.string.comment_title),
                text = comment,
                visibleAll = commentVisible,
                onVisibleAllChanged = viewModel::onCommentVisibleChanged,
                onTextChanged = viewModel::onCommentChanged
            )

            VerticalSpacer(height = 40.dp)
        }
    }
}