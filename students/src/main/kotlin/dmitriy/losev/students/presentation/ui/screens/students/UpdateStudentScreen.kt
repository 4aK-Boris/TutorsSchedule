package dmitriy.losev.students.presentation.ui.screens.students

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import dmitriy.losev.students.presentation.ui.views.AddContactButton
import dmitriy.losev.students.presentation.ui.views.ContactsView
import dmitriy.losev.students.presentation.viewmodels.students.UpdateStudentScreenViewModel
import dmitriy.losev.ui.views.TitleWithSaveIcon
import dmitriy.losev.ui.views.VerticalSpacer
import dmitriy.losev.ui.views.boxes.ColumnSecondaryWithLoader
import dmitriy.losev.ui.views.buttons.ContactIconButton
import dmitriy.losev.ui.views.popups.EditContactPopUp
import dmitriy.losev.ui.views.textfields.CommentTextField
import dmitriy.losev.ui.views.textfields.DefaultTextField
import dmitriy.losev.ui.views.textfields.DiscordTextField
import dmitriy.losev.ui.views.textfields.EmailTextField
import dmitriy.losev.ui.views.textfields.PhoneTextField
import dmitriy.losev.ui.views.textfields.SkypeTextField
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun UpdateStudentScreen(
    studentId: String,
    studentsNavigationListener: StudentsNavigationListener,
    viewModel: UpdateStudentScreenViewModel = koinViewModel()
) {

    val firstName by viewModel.firstName.collectAsState()
    val lastName by viewModel.lastName.collectAsState()
    val patronymic by viewModel.patronymic.collectAsState()
    val phoneNumber by viewModel.phoneNumber.collectAsState()
    val email by viewModel.email.collectAsState()
    val skype by viewModel.skype.collectAsState()
    val discord by viewModel.discord.collectAsState()
    val comment by viewModel.comment.collectAsState()
    val contacts by viewModel.contacts.collectAsState()

    val contact by viewModel.contact.collectAsState()
    val editContactPopUpVisible by viewModel.editContactPopUpVisible.collectAsState()

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

    LaunchedEffect(key1 = Unit) {
        viewModel.loadData(studentId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TitleWithSaveIcon(
                title = stringResource(id = R.string.update_student_screen_title),
                back = { viewModel.back(studentsNavigationListener) },
                save = { viewModel.saveChanges(studentsNavigationListener, studentId) }
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

            EmailTextField(
                title = stringResource(id = R.string.email_title),
                email = email,
                onEmailChanged = viewModel::onEmailChanged,
                imeAction = ImeAction.Next,
                textFieldState = emailTextFieldState,
                clearTextFieldState = viewModel::clearEmailTextFieldState
            )

            VerticalSpacer(height = 24.dp)

            PhoneTextField(
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

            VerticalSpacer(height = 36.dp)

            ContactsView(
                contacts = contacts,
                openContact = { contactId -> viewModel.navigateToContactScreen(studentsNavigationListener, studentId, contactId) },
                openPopUp = { contact -> viewModel.openEditContactPopUp(contact) }
            )

            VerticalSpacer(height = 24.dp)

            AddContactButton {
                viewModel.navigateToAddContactScreen(studentsNavigationListener, studentId)
            }

            VerticalSpacer(height = 40.dp)
        }
    }

    EditContactPopUp(
        visible = editContactPopUpVisible,
        contactName = contact?.shortName,
        onEditClicked = { viewModel.navigateToEditContactScreen(studentsNavigationListener, studentId) },
        onDeleteClicked = { viewModel.deleteContact(studentId) },
        close = viewModel::closeEditContactPopUp
    )
}
