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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dmitriy.losev.ui.R
import dmitriy.losev.ui.theme.TutorsScheduleTheme
import dmitriy.losev.ui.views.Title1Text
import dmitriy.losev.ui.views.VerticalSpacer
import dmitriy.losev.ui.views.buttons.PrimaryButton
import dmitriy.losev.ui.views.buttons.SecondaryButton

@Composable
fun EmailPopUp(visible: Boolean, close: () -> Unit, sendMail: () -> Unit) {

    DefaultPopUp(visible = visible, close = close) {

        Title1Text(text = stringResource(id = R.string.email_pop_up_title))
        
        VerticalSpacer(height = 56.dp)

        PrimaryButton(text = stringResource(id = R.string.send_mail), onClick = close)

        VerticalSpacer(height = 16.dp)

        SecondaryButton(text = stringResource(id = R.string.back), onClick = sendMail)
        
        VerticalSpacer(height = 40.dp)
    }
}

@Composable
@Preview
private fun EmailPopUpPreviewWithLightTheme() {

    var visible by remember { mutableStateOf(value = false) }

    val close = { visible = false }

    val open = { visible = true }

    TutorsScheduleTheme(darkTheme = false) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PrimaryButton(text = "Открыть", onClick = open)

            EmailPopUp(visible = visible, close = close, sendMail = close)
        }
    }
}

@Composable
@Preview
private fun EmailPopUpPreviewWithDarkTheme() {

    var visible by remember { mutableStateOf(value = false) }

    val close = { visible = false }

    val open = { visible = true }

    TutorsScheduleTheme(darkTheme = true) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PrimaryButton(text = "Открыть", onClick = open)

            EmailPopUp(visible = visible, close = close, sendMail = close)
        }
    }
}