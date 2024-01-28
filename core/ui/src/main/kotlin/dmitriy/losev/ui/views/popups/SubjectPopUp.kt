package dmitriy.losev.ui.views.popups

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import dmitriy.losev.ui.core.SUBJECT_NAME
import dmitriy.losev.ui.theme.TutorsScheduleTheme
import dmitriy.losev.ui.views.Title1Text
import dmitriy.losev.ui.views.VerticalSpacer
import dmitriy.losev.ui.views.buttons.PrimaryButton
import dmitriy.losev.ui.views.buttons.SecondaryButton

@Composable
fun SubjectPopUp(
    visible: Boolean,
    subjectName: String,
    onEditClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    close: () -> Unit
) {

    DefaultPopUp(visible = visible, close = close) {

        VerticalSpacer(height = 10.dp)

        Title1Text(text = subjectName, modifier = Modifier.padding(horizontal = 16.dp))

        VerticalSpacer(height = 24.dp)

        PopUpTextWithIconButton(icon = painterResource(id = R.drawable.edit_primary), text = stringResource(id = R.string.edit), onClick = onEditClicked)

        VerticalSpacer(height = 16.dp)

        PopUpTextWithIconButton(icon = painterResource(id = R.drawable.delete), text = stringResource(id = R.string.delete), onClick = onDeleteClicked)

        VerticalSpacer(height = 64.dp)

        SecondaryButton(text = stringResource(id = R.string.cansel), onClick = close)

        VerticalSpacer(height = 40.dp)
    }
}

@Composable
@Preview
private fun SubjectPopUpPreviewWithLightTheme() {

    var visible by remember { mutableStateOf(value = false) }

    val close = { visible = false }

    val open = { visible = true }

    TutorsScheduleTheme(darkTheme = false) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PrimaryButton(text = "Открыть", onClick = open)

            SubjectPopUp(
                visible = visible,
                subjectName = SUBJECT_NAME,
                onEditClicked = close,
                onDeleteClicked = close,
                close = close
            )
        }
    }
}

@Composable
@Preview
private fun SubjectStudentPopUpPreviewWithDarkTheme() {

    var visible by remember { mutableStateOf(value = false) }

    val close = { visible = false }

    val open = { visible = true }

    TutorsScheduleTheme(darkTheme = true) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PrimaryButton(text = "Открыть", onClick = open)

            SubjectPopUp(
                visible = visible,
                subjectName = SUBJECT_NAME,
                onEditClicked = close,
                onDeleteClicked = close,
                close = close
            )
        }
    }
}