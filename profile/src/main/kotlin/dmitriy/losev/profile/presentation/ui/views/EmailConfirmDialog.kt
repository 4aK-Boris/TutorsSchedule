package dmitriy.losev.profile.presentation.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MarkEmailUnread
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import dmitriy.losev.core.theme.TutorsScheduleTheme
import dmitriy.losev.profile.R

@Composable
fun EmailConfirmDialog(
    state: Boolean,
    text: String,
    emailTimer: Int,
    hasDelayExpired: Boolean,
    close: () -> Unit,
    onClick: () -> Unit,
) {
    if (state) {

        val resendString = stringResource(R.string.resend)

        val confirmButtonText = if (hasDelayExpired) {
            stringResource(R.string.send_message)
        } else {
            "$resendString $emailTimer"
        }

        Dialog(
            onDismissRequest = close,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                securePolicy = SecureFlagPolicy.SecureOff
            )
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = TutorsScheduleTheme.colors.backgroundPrimary)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 32.dp, start = 16.dp, end = 16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.MarkEmailUnread,
                            contentDescription = stringResource(R.string.email),
                            modifier = Modifier.size(size = 30.dp)
                        )

                        IconButton(onClick = close, modifier = Modifier.size(size = 30.dp)) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = stringResource(id = R.string.close),
                                modifier = Modifier.size(size = 30.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(height = 16.dp))

                    Text(
                        text = stringResource(id = R.string.email_confirm_dialog_title),
                        style = TutorsScheduleTheme.typography.dialogTitle
                    )

                    Spacer(modifier = Modifier.height(height = 16.dp))

                    Text(text = text, style = TutorsScheduleTheme.typography.dialogText)

                    Spacer(modifier = Modifier.height(height = 16.dp))

                    Text(
                        text = confirmButtonText,
                        style = TutorsScheduleTheme.typography.dialogButtonText,
                        modifier = Modifier.clickable(enabled = hasDelayExpired, onClick = onClick)
                    )
                }
            }
        }
    }
}