package dmitriy.losev.students.presentation.ui.screens.contact

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
import dmitriy.losev.students.R
import dmitriy.losev.students.core.StudentsNavigationListener
import dmitriy.losev.students.presentation.viewmodels.contact.ContactScreenViewModel
import dmitriy.losev.ui.views.TitleWithEditIcon
import dmitriy.losev.ui.views.VerticalSpacer
import dmitriy.losev.ui.views.boxes.ColumnSecondaryWithLoader
import dmitriy.losev.ui.views.popups.ContactCallPopUp
import dmitriy.losev.ui.views.popups.ContactMessagePopUp
import dmitriy.losev.ui.views.popups.ContactPopUp
import dmitriy.losev.ui.views.textfields.DefaultTextField
import dmitriy.losev.ui.views.textfields.PhoneTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun ContactScreen(
    studentId: String,
    contactId: String,
    studentsNavigationListener: StudentsNavigationListener,
    viewModel: ContactScreenViewModel = koinViewModel()
) {

    val firstName by viewModel.firstName.collectAsState()
    val lastName by viewModel.lastName.collectAsState()
    val patronymic by viewModel.patronymic.collectAsState()
    val phoneNumber by viewModel.phoneNumber.collectAsState()

    val shortName by viewModel.shortName.collectAsState()

    val contactPopUpVisible by viewModel.contactPopUpVisible.collectAsState()
    val contactCallPopUpVisible by viewModel.contactCallPopUpVisible.collectAsState()
    val contactMessagePopUpVisible by viewModel.contactMessagePopUpVisible.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.loadContact(studentId, contactId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TitleWithEditIcon(
                title = stringResource(id = R.string.contact_screen_title),
                back = { viewModel.back(studentsNavigationListener) },
                edit = viewModel::openContactPopUp
            )
        }
    ) { paddingValues ->

        ColumnSecondaryWithLoader(modifier = Modifier.padding(paddingValues = paddingValues), viewModel = viewModel) {

            VerticalSpacer(height = 24.dp)

            DefaultTextField(
                title = stringResource(id = R.string.first_name_title),
                hint = stringResource(id = R.string.first_name_title),
                text = firstName,
                enabled = false
            )

            VerticalSpacer(height = 24.dp)

            DefaultTextField(
                title = stringResource(id = R.string.last_name_title),
                hint = stringResource(id = R.string.last_name_title),
                text = lastName,
                enabled = false
            )

            VerticalSpacer(height = 24.dp)

            DefaultTextField(
                title = stringResource(id = R.string.patronymic_title),
                hint = stringResource(id = R.string.patronymic_title),
                text = patronymic,
                enabled = false
            )

            VerticalSpacer(height = 24.dp)

            PhoneTextField(
                title = stringResource(id = R.string.phone_number_title),
                phone = phoneNumber,
                enabled = false
            )

            VerticalSpacer(height = 40.dp)
        }
    }

    ContactPopUp(
        visible = contactPopUpVisible,
        contactName = shortName,
        onEditClicked = { viewModel.navigateToUpdateContactScreen(studentsNavigationListener, studentId, contactId) },
        onCallClicked = viewModel::openContactCallPopUp,
        onWriteClicked = viewModel::openContactMessagePopUp,
        onDeleteClicked = { viewModel.deleteContact(studentsNavigationListener, studentId, contactId) },
        close = viewModel::closeContactPopUp
    )

    ContactCallPopUp(
        visible = contactCallPopUpVisible,
        contactName = shortName,
        onPhoneClicked = viewModel::onPhoneCallClicked,
        onViberClicked = viewModel::onViberClicked,
        onWhatsAppClicked = viewModel::onWhatsAppClicked,
        onTelegramClicked = viewModel::onTelegramClicked,
        close = viewModel::closeContactCallPopUp
    )

    ContactMessagePopUp(
        visible = contactMessagePopUpVisible,
        contactName = shortName,
        onSmsClicked = viewModel::onSmsClicked,
        onTelegramClicked = viewModel::onTelegramClicked,
        onWhatsAppClicked = viewModel::onWhatsAppClicked,
        onViberClicked = viewModel::onViberClicked, close = viewModel::closeContactMessagePopUp
    )
}
