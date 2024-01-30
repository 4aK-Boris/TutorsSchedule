package dmitriy.losev.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dmitriy.losev.ui.core.TITLE
import dmitriy.losev.ui.theme.LocalTutorsScheduleColors
import dmitriy.losev.ui.theme.TutorsScheduleTheme
import dmitriy.losev.ui.views.buttons.ButtonBack
import dmitriy.losev.ui.views.buttons.EditIconButton
import dmitriy.losev.ui.views.buttons.SaveIconButton

@Composable
fun Title(title: String, back: () -> Unit) {

    val backgroundColor = LocalTutorsScheduleColors.current.backgroundTitle

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor)
            .height(height = 48.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        ButtonBack(onClick = back)

        HorizontalSpacer(width = 12.dp)

        Title2Text(text = title)
    }
}

@Composable
fun TitleWithSaveIcon(title: String, back: () -> Unit, save: () -> Unit) {

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val backgroundColor = LocalTutorsScheduleColors.current.backgroundTitle

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor)
            .height(height = 48.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ButtonBack(onClick = back)

            HorizontalSpacer(width = 12.dp)

            Title2Text(text = title, modifier = Modifier.widthIn(max = screenWidth - 116.dp))
        }

        SaveIconButton(onClick = save)
    }
}

@Composable
fun TitleWithEditIcon(title: String, back: () -> Unit, edit: () -> Unit) {

    val backgroundColor = LocalTutorsScheduleColors.current.backgroundTitle

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor)
            .height(height = 48.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ButtonBack(onClick = back)

            HorizontalSpacer(width = 12.dp)

            Title2Text(text = title)
        }

        EditIconButton(onClick = edit)
    }
}

@Preview(group = "Title")
@Composable
private fun TitleWithLightTheme() {

    TutorsScheduleTheme(darkTheme = false) {
        Title(title = TITLE, back = { })
    }
}

@Preview(group = "TitleW")
@Composable
private fun TitleWithDarkTheme() {

    TutorsScheduleTheme(darkTheme = true) {
        Title(title = TITLE, back = { })
    }
}

@Preview(group = "TitleWithSaveIcon")
@Composable
private fun TitleWithSaveIconWithLightTheme() {

    TutorsScheduleTheme(darkTheme = false) {
        TitleWithSaveIcon(title = TITLE, back = { }, save = { })
    }
}

@Preview(group = "TitleWithSaveIcon")
@Composable
private fun TitleWithSaveIconWithDarkTheme() {

    TutorsScheduleTheme(darkTheme = true) {
        TitleWithSaveIcon(title = TITLE, back = { }, save = { })
    }
}

