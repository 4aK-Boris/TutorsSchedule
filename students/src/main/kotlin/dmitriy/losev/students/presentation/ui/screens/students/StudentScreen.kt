package dmitriy.losev.students.presentation.ui.screens.students

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
import dmitriy.losev.students.presentation.ui.views.AddContactButton
import dmitriy.losev.students.presentation.ui.views.ContactsView
import dmitriy.losev.students.presentation.ui.views.StudentMenuItem
import dmitriy.losev.students.presentation.viewmodels.students.StudentScreenViewModel
import dmitriy.losev.ui.views.TitleWithEditIcon
import dmitriy.losev.ui.views.VerticalSpacer
import dmitriy.losev.ui.views.boxes.ColumnSecondaryWithLoader
import dmitriy.losev.ui.views.popups.ContactCallPopUp
import dmitriy.losev.ui.views.popups.ContactMessagePopUp
import dmitriy.losev.ui.views.popups.ContactPopUp
import dmitriy.losev.ui.views.textfields.CommentTextField
import dmitriy.losev.ui.views.textfields.DefaultTextField
import dmitriy.losev.ui.views.textfields.DiscordTextField
import dmitriy.losev.ui.views.textfields.EmailTextField
import dmitriy.losev.ui.views.textfields.PhoneTextField
import dmitriy.losev.ui.views.textfields.SkypeTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun StudentScreen(
    studentId: String,
    studentsNavigationListener: StudentsNavigationListener,
    viewModel: StudentScreenViewModel = koinViewModel()
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

    val commentVisible by viewModel.commentVisible.collectAsState()

    val contact by viewModel.contact.collectAsState()

    val contactPopUpVisible by viewModel.contactPopUpVisible.collectAsState()
    val contactCallPopUpVisible by viewModel.contactCallPopUpVisible.collectAsState()
    val contactMessagePopUpVisible by viewModel.contactMessagePopUpVisible.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.loadData(studentId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TitleWithEditIcon(
                title = stringResource(id = R.string.student_screen_title),
                back = { viewModel.back(studentsNavigationListener) },
                edit = { viewModel.navigateToUpdateStudentScreen(studentsNavigationListener, studentId) }
            )
        }
    ) { paddingValues ->

        ColumnSecondaryWithLoader(modifier = Modifier.padding(paddingValues = paddingValues), viewModel = viewModel) {

            VerticalSpacer(height = 24.dp)

            DefaultTextField(title = stringResource(id = R.string.first_name_title), hint = stringResource(id = R.string.first_name_title), text = firstName, enabled = false)

            VerticalSpacer(height = 24.dp)

            DefaultTextField(title = stringResource(id = R.string.last_name_title), hint = stringResource(id = R.string.last_name_title), text = lastName, enabled = false)

            VerticalSpacer(height = 24.dp)

            DefaultTextField(title = stringResource(id = R.string.patronymic_title), hint = stringResource(id = R.string.patronymic_title), text = patronymic, enabled = false)

            VerticalSpacer(height = 24.dp)

            EmailTextField(title = stringResource(id = R.string.email_title), email = email, enabled = false)

            VerticalSpacer(height = 24.dp)

            PhoneTextField(title = stringResource(id = R.string.phone_number_title), phone = phoneNumber, enabled = false)

            VerticalSpacer(height = 24.dp)

            SkypeTextField(skype = skype, enabled = false)

            VerticalSpacer(height = 24.dp)

            DiscordTextField(discord = discord, enabled = false)

            VerticalSpacer(height = 24.dp)

            StudentMenuItem(title = stringResource(id = R.string.upcoming_lessons_title), onClick = {  }, onMenuClick = {  })

            VerticalSpacer(height = 24.dp)

            StudentMenuItem(title = stringResource(id = R.string.student_groups_title), onClick = {  }, onMenuClick = {  })

            VerticalSpacer(height = 24.dp)

            ContactsView(
                contacts = contacts,
                openPopUp = viewModel::onContactChanged,
                openContact = { contactId -> viewModel.navigateToContactScreen(studentsNavigationListener, studentId, contactId) }
            )

            VerticalSpacer(height = 24.dp)

            AddContactButton {
                viewModel.navigateToAddContactScreen(studentsNavigationListener, studentId)
            }

            VerticalSpacer(height = 24.dp)

            CommentTextField(
                title = stringResource(id = R.string.comment_title),
                text = comment,
                visibleAll = commentVisible,
                onVisibleAllChanged = viewModel::onCommentVisibleChanged
            )

            VerticalSpacer(height = 40.dp)
        }
    }

    ContactPopUp(
        visible = contactPopUpVisible,
        contactName = contact?.shortName,
        onEditClicked = { viewModel.navigateToUpdateContactScreen(studentsNavigationListener, studentId) },
        onCallClicked = viewModel::openContactCallPopUp,
        onWriteClicked = viewModel::openContactMessagePopUp,
        onDeleteClicked = { viewModel.deleteContact(studentId) },
        close = viewModel::closeContactPopUp
    )

    ContactCallPopUp(
        visible = contactCallPopUpVisible,
        contactName = contact?.shortName,
        onPhoneClicked = viewModel::onPhoneCallClicked,
        onViberClicked = viewModel::onViberClicked,
        onWhatsAppClicked = viewModel::onWhatsAppClicked,
        onTelegramClicked = viewModel::onTelegramClicked,
        close = viewModel::closeContactCallPopUp
    )

    ContactMessagePopUp(
        visible = contactMessagePopUpVisible,
        contactName = contact?.shortName,
        onSmsClicked = viewModel::onSmsClicked,
        onTelegramClicked = viewModel::onTelegramClicked,
        onWhatsAppClicked = viewModel::onWhatsAppClicked,
        onViberClicked = viewModel::onViberClicked,
        close = viewModel::closeContactMessagePopUp
    )
}


