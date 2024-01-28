package dmitriy.losev.ui.views.popups

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dmitriy.losev.ui.R
import dmitriy.losev.ui.core.CONTACT_NAME
import dmitriy.losev.ui.theme.TutorsScheduleTheme
import dmitriy.losev.ui.views.Title1Text
import dmitriy.losev.ui.views.VerticalSpacer
import dmitriy.losev.ui.views.buttons.PrimaryButton
import dmitriy.losev.ui.views.buttons.SecondaryButton

@Composable
fun ContactPopUp(
    visible: Boolean,
    contactName: String?,
    onEditClicked: () -> Unit,
    onCallClicked: () -> Unit,
    onWriteClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    close: () -> Unit
) {

    DefaultPopUp(visible = visible, close = close) {

        VerticalSpacer(height = 10.dp)

        Title1Text(text = "Контакты $contactName")

        VerticalSpacer(height = 24.dp)

        PopUpTextWithIconButton(icon = painterResource(id = R.drawable.edit_primary), text = stringResource(id = R.string.edit), onClick = onEditClicked)

        VerticalSpacer(height = 16.dp)

        PopUpTextWithIconButton(icon = painterResource(id = R.drawable.phone), text = stringResource(id = R.string.call), onClick = onCallClicked)

        VerticalSpacer(height = 16.dp)

        PopUpTextWithIconButton(icon = painterResource(id = R.drawable.sms), text = stringResource(id = R.string.white), onClick = onWriteClicked)

        VerticalSpacer(height = 16.dp)

        PopUpTextWithIconButton(icon = painterResource(id = R.drawable.delete), text = stringResource(id = R.string.delete), onClick = onDeleteClicked)

        VerticalSpacer(height = 82.dp)

        SecondaryButton(text = stringResource(id = R.string.cansel), onClick = close)

        VerticalSpacer(height = 40.dp)
    }
}

@Composable
@Preview
private fun ContactPopUpPreviewWithLightTheme() {

    var visible by remember { mutableStateOf(value = false) }

    val close = { visible = false }

    val open = { visible = true }

    TutorsScheduleTheme(darkTheme = false) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PrimaryButton(text = "Открыть", onClick = open)

            ContactPopUp(
                visible = visible,
                contactName = CONTACT_NAME,
                onEditClicked = close,
                onCallClicked = close,
                onDeleteClicked = close,
                onWriteClicked = close,
                close = close
            )
        }
    }
}

@Composable
@Preview
private fun ContactStudentPopUpPreviewWithDarkTheme() {

    var visible by remember { mutableStateOf(value = false) }

    val close = { visible = false }

    val open = { visible = true }

    TutorsScheduleTheme(darkTheme = true) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PrimaryButton(text = "Открыть", onClick = open)

            ContactPopUp(
                visible = visible,
                contactName = CONTACT_NAME,
                onEditClicked = close,
                onCallClicked = close,
                onDeleteClicked = close,
                onWriteClicked = close,
                close = close
            )
        }
    }
}