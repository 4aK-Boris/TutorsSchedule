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
fun CanselDialog(state: Boolean, close: () -> Unit, noSave: () -> Unit, save: () -> Unit) {
    if (state) {
        AlertDialog(
            onDismissRequest = close,
            title = {
                Text(
                    text = stringResource(id = R.string.save_changes_dialog_title),
                    style = TutorsScheduleTheme.typography.dialogTitle
                )
            },
            dismissButton = {
                Text(
                    text = stringResource(id = R.string.no_save),
                    style = TutorsScheduleTheme.typography.dialogButtonText,
                    modifier = Modifier.clickable(onClick = noSave)
                )
            },
            confirmButton = {
                Text(
                    text = stringResource(id = R.string.save),
                    style = TutorsScheduleTheme.typography.dialogButtonText,
                    modifier = Modifier.clickable(onClick = save)
                )
            }
        )
    }
}