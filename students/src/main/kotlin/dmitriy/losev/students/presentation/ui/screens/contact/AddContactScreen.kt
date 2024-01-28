package dmitriy.losev.students.presentation.ui.screens.contact

import android.Manifest
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
import dmitriy.losev.students.presentation.viewmodels.contact.AddContactScreenViewModel
import dmitriy.losev.ui.views.TitleWithSaveIcon
import dmitriy.losev.ui.views.VerticalSpacer
import dmitriy.losev.ui.views.boxes.ColumnSecondaryWithLoader
import dmitriy.losev.ui.views.buttons.ContactIconButton
import dmitriy.losev.ui.views.textfields.DefaultTextField
import dmitriy.losev.ui.views.textfields.PhoneTextField
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AddContactScreen(
    studentId: String,
    studentsNavigationListener: StudentsNavigationListener,
    viewModel: AddContactScreenViewModel = koinViewModel()
) {

    val firstName by viewModel.firstName.collectAsState()
    val lastName by viewModel.lastName.collectAsState()
    val patronymic by viewModel.patronymic.collectAsState()
    val phoneNumber by viewModel.phoneNumber.collectAsState()

    val firstNameTextFieldState by viewModel.firstNameTextFieldState.collectAsState()
    val phoneNumberTextFieldState by viewModel.phoneNumberTextFieldState.collectAsState()

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        viewModel.pickContactResult(result)
    }

    val phoneNumberPermissionState = rememberPermissionState(
        Manifest.permission.READ_CONTACTS
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TitleWithSaveIcon(
                title = stringResource(id = R.string.add_contact_screen_title),
                back = { viewModel.back(studentsNavigationListener) },
                save = { viewModel.saveContact(studentsNavigationListener, studentId) }
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

            VerticalSpacer(height = 40.dp)
        }
    }
}
