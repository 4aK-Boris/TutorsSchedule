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
fun ContactMessagePopUp(
    visible: Boolean,
    contactName: String?,
    onSmsClicked: () -> Unit,
    onTelegramClicked: () -> Unit,
    onWhatsAppClicked: () -> Unit,
    onViberClicked: () -> Unit,
    close: () -> Unit
) {

    DefaultPopUp(visible = visible, close = close) {

        VerticalSpacer(height = 10.dp)

        Title1Text(text = "Написать $contactName")

        VerticalSpacer(height = 24.dp)

        PopUpTextWithIconButton(icon = painterResource(id = R.drawable.sms), text = stringResource(id = R.string.sms), onClick = onSmsClicked)

        VerticalSpacer(height = 16.dp)

        PopUpTextWithIconButton(icon = painterResource(id = R.drawable.telegram), text = stringResource(id = R.string.telegram), onClick = onTelegramClicked)

        VerticalSpacer(height = 16.dp)

        PopUpTextWithIconButton(icon = painterResource(id = R.drawable.whats_app), text = stringResource(id = R.string.whats_app), onClick = onWhatsAppClicked)

        VerticalSpacer(height = 16.dp)

        PopUpTextWithIconButton(icon = painterResource(id = R.drawable.viber), text = stringResource(id = R.string.viber), onClick = onViberClicked)

        VerticalSpacer(height = 42.dp)

        SecondaryButton(text = stringResource(id = R.string.cansel), onClick = close)

        VerticalSpacer(height = 40.dp)
    }
}

@Composable
@Preview
private fun ContactMessagePopUpPreviewWithLightTheme() {

    var visible by remember { mutableStateOf(value = false) }

    val close = { visible = false }

    val open = { visible = true }

    TutorsScheduleTheme(darkTheme = false) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PrimaryButton(text = "Открыть", onClick = open)

            ContactMessagePopUp(
                visible = visible,
                contactName = CONTACT_NAME,
                onSmsClicked = close,
                onViberClicked = close,
                onWhatsAppClicked = close,
                onTelegramClicked = close,
                close = close
            )
        }
    }
}

@Composable
@Preview
private fun ContactMessageStudentPopUpPreviewWithDarkTheme() {

    var visible by remember { mutableStateOf(value = false) }

    val close = { visible = false }

    val open = { visible = true }

    TutorsScheduleTheme(darkTheme = true) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PrimaryButton(text = "Открыть", onClick = open)

            ContactMessagePopUp(
                visible = visible,
                contactName = CONTACT_NAME,
                onSmsClicked = close,
                onViberClicked = close,
                onWhatsAppClicked = close,
                onTelegramClicked = close,
                close = close
            )
        }
    }
}