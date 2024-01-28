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
import dmitriy.losev.ui.views.buttons.CriticalButton
import dmitriy.losev.ui.views.buttons.PrimaryButton

@Composable
fun DeleteProfilePopUp(visible: Boolean, close: () -> Unit, delete: () -> Unit) {

    DefaultPopUp(visible = visible, close = close) {

        VerticalSpacer(height = 12.dp)

        Title1Text(text = stringResource(id = R.string.delete_message))

        VerticalSpacer(height = 20.dp)

        PrimaryButton(text = stringResource(id = R.string.no), onClick = close)

        VerticalSpacer(height = 16.dp)

        CriticalButton(text = stringResource(id = R.string.delete), onClick = delete)

        VerticalSpacer(height = 40.dp)
    }
}

@Composable
@Preview
private fun DeleteProfilePopUpPreviewWithLightTheme() {

    var visible by remember { mutableStateOf(value = false) }

    val close = { visible = false }

    val open = { visible = true }

    TutorsScheduleTheme(darkTheme = false) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PrimaryButton(text = "Открыть", onClick = open)

            DeleteProfilePopUp(visible = visible, close = close, delete = close)
        }
    }
}

@Composable
@Preview
private fun DeleteProfilePopUpPreviewWithDarkTheme() {

    var visible by remember { mutableStateOf(value = false) }

    val close = { visible = false }

    val open = { visible = true }

    TutorsScheduleTheme(darkTheme = true) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PrimaryButton(text = "Открыть", onClick = open)

            DeleteProfilePopUp(visible = visible, close = close, delete = close)
        }
    }
}