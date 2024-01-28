package dmitriy.losev.students.presentation.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dmitriy.losev.core.models.Contact
import dmitriy.losev.students.R
import dmitriy.losev.ui.theme.LocalTutorsScheduleColors
import dmitriy.losev.ui.views.Title2Text
import dmitriy.losev.ui.views.VerticalSpacer

@Composable
fun ContactsView(contacts: List<Contact>, openPopUp: (Contact) -> Unit, openContact: (String) -> Unit) {

    val backgroundColor = LocalTutorsScheduleColors.current.backgroundPrimary

    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start) {

        Title2Text(text = stringResource(id = R.string.additional_contacts_title), modifier = Modifier.padding(horizontal = 16.dp))

        VerticalSpacer(height = 12.dp)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = backgroundColor),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            contacts.forEach { contact ->

                StudentMenuItem(
                    title = contact.name,
                    onClick = { openContact(contact.id) },
                    onMenuClick = { openPopUp(contact) }
                )
            }
        }
    }
}
