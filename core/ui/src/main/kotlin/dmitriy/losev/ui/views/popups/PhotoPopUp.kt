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
import dmitriy.losev.ui.views.buttons.HintButton
import dmitriy.losev.ui.views.buttons.PrimaryButton

@Composable
fun PhotoPopUp(visible: Boolean, close: () -> Unit, chooseFromGallery: () -> Unit, createPhoto: () -> Unit) {

    DefaultPopUp(visible = visible, close = close) {

        Title1Text(text = stringResource(id = R.string.photo_profile))
        
        VerticalSpacer(height = 96.dp)

        PrimaryButton(text = stringResource(id = R.string.choose_from_the_gallery), onClick = chooseFromGallery)

        VerticalSpacer(height = 16.dp)

        HintButton(text = stringResource(id = R.string.take_photo), onClick = createPhoto)

        VerticalSpacer(height = 28.dp)
    }
}

@Composable
@Preview
private fun PhotoPopUpPreviewWithLightTheme() {

    var visible by remember { mutableStateOf(value = false) }

    val close = { visible = false }

    val open = { visible = true }

    TutorsScheduleTheme(darkTheme = false) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PrimaryButton(text = "Открыть", onClick = open)

            PhotoPopUp(visible = visible, close = close, chooseFromGallery = close, createPhoto = close)
        }
    }
}

@Composable
@Preview
private fun PhotoPopUpPreviewWithDarkTheme() {

    var visible by remember { mutableStateOf(value = false) }

    val close = { visible = false }

    val open = { visible = true }

    TutorsScheduleTheme(darkTheme = true) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PrimaryButton(text = "Открыть", onClick = open)

            PhotoPopUp(visible = visible, close = close, chooseFromGallery = close, createPhoto = close)
        }
    }
}