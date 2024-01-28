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
import dmitriy.losev.ui.core.EMPTY_STRING
import dmitriy.losev.ui.theme.TutorsScheduleTheme
import dmitriy.losev.ui.views.Title1Text
import dmitriy.losev.ui.views.VerticalSpacer
import dmitriy.losev.ui.views.buttons.PrimaryButton
import dmitriy.losev.ui.views.buttons.SecondaryButton

@Composable
fun MenuStudentPopUp(
    visible: Boolean,
    isActive: Boolean,
    studentName: String?,
    editProfile: () -> Unit,
    call: () -> Unit,
    write: () -> Unit,
    moveToArchive: () -> Unit,
    bringItBack: () -> Unit,
    makePayment: () -> Unit,
    delete: () -> Unit,
    close: () -> Unit
) {

    DefaultPopUp(visible = visible, close = close) {

        VerticalSpacer(height = 10.dp)

        Title1Text(text = studentName ?: EMPTY_STRING)

        VerticalSpacer(height = 24.dp)

        PopUpTextWithIconButton(icon = painterResource(id = R.drawable.edit_primary), text = stringResource(id = R.string.edit_profile), onClick = editProfile)

        VerticalSpacer(height = 16.dp)

        PopUpTextWithIconButton(icon = painterResource(id = R.drawable.phone), text = stringResource(id = R.string.call), onClick = call)

        VerticalSpacer(height = 16.dp)

        PopUpTextWithIconButton(icon = painterResource(id = R.drawable.write), text = stringResource(id = R.string.white), onClick = write)

        VerticalSpacer(height = 16.dp)

        if (isActive) {

            PopUpTextWithIconButton(
                icon = painterResource(id = R.drawable.archive),
                text = stringResource(id = R.string.move_to_archive),
                onClick = moveToArchive
            )
        } else {
            PopUpTextWithIconButton(
                icon = painterResource(id = R.drawable.bring_it_back),
                text = stringResource(id = R.string.bring_it_back_from_archive),
                onClick = bringItBack
            )
        }

        VerticalSpacer(height = 16.dp)

        PopUpTextWithIconButton(icon = painterResource(id = R.drawable.balance), text = stringResource(id = R.string.make_payment), onClick = makePayment)

        if (!isActive) {

            VerticalSpacer(height = 16.dp)

            PopUpTextWithIconButton(icon = painterResource(id = R.drawable.delete), text = stringResource(id = R.string.delete), onClick = delete)
        }

        VerticalSpacer(height = 20.dp)

        SecondaryButton(text = stringResource(id = R.string.cansel), onClick = close)

        VerticalSpacer(height = 40.dp)
    }
}

@Composable
@Preview
private fun MenuStudentPopUpPreviewWithLightTheme() {

    var visible by remember { mutableStateOf(value = false) }

    val close = { visible = false }

    val open = { visible = true }

    TutorsScheduleTheme(darkTheme = false) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PrimaryButton(text = "Открыть", onClick = open)

//            MenuStudentPopUp(
//                visible = visible,
//                studentName = CONTACT_NAME,
//                editProfile = close,
//                call = close,
//                write = close,
//                moveToArchive = close,
//                makePayment = close,
//                close = close
//            )
        }
    }
}

@Composable
@Preview
private fun MenuStudentPopUpPreviewWithDarkTheme() {

    var visible by remember { mutableStateOf(value = false) }

    val close = { visible = false }

    val open = { visible = true }

    TutorsScheduleTheme(darkTheme = true) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PrimaryButton(text = "Открыть", onClick = open)

//            MenuStudentPopUp(
//                visible = visible,
//                studentName = CONTACT_NAME,
//                editProfile = close,
//                call = close,
//                write = close,
//                moveToArchive = close,
//                makePayment = close,
//                close = close
//            )
        }
    }
}