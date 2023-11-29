package dmitriy.losev.profile.presentation.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dmitriy.losev.core.theme.TutorsScheduleTheme
import dmitriy.losev.profile.R

@Composable
fun DeleteAccountDialog(
    state: Boolean,
    close: () -> Unit,
    onClick: () -> Unit
) {
    if (state) {
        AlertDialog(
            onDismissRequest = close,
            title = {
                Text(
                    text = stringResource(id = R.string.delete_account_dialog_title),
                    style = TutorsScheduleTheme.typography.dialogTitle
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.delete_account_dialog_text),
                    style = TutorsScheduleTheme.typography.dialogText
                )
            },
            dismissButton = {
                Text(
                    text = stringResource(id = R.string.delete_account_refuse_button_text),
                    style = TutorsScheduleTheme.typography.dialogButtonText,
                    modifier = Modifier.clickable(onClick = close)
                )
            },
            confirmButton = {
                Text(
                    text = stringResource(id = R.string.delete_account_confirm_button_text),
                    style = TutorsScheduleTheme.typography.dialogButtonText,
                    modifier = Modifier.clickable(onClick = onClick)
                )
            }
        )
    }
}