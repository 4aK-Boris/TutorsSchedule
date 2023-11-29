package dmitriy.losev.core.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dmitriy.losev.core.R
import dmitriy.losev.core.theme.TutorsScheduleTheme
import kotlinx.coroutines.delay
import org.koin.core.component.KoinComponent

abstract class BaseActivity : ComponentActivity(), KoinComponent {

    abstract val baseViewModel: BaseViewModel

    @Composable
    abstract fun UI(modifier: Modifier)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TutorsScheduleTheme {

                Surface(modifier = Modifier.fillMaxSize()) {
                    val errorMessage by baseViewModel.errorMessage.collectAsState()
                    val errorState by baseViewModel.errorState.collectAsState()

                    UI(modifier = Modifier)

                    ErrorDialog(state = errorState, errorMessage = errorMessage, close = baseViewModel.close)

                    LaunchedEffect(key1 = errorState, key2 = errorMessage) {
                        if (errorState) {
                            delay(4000L)
                            baseViewModel.close()
                        }
                    }
                }

            }
        }
    }

    @Composable
    fun ErrorDialog(state: Boolean, errorMessage: String, close: () -> Unit) {
        if (state) {
            AlertDialog(
                onDismissRequest = close,
                icon = {
                    Icon(
                        imageVector = Icons.Default.ErrorOutline,
                        contentDescription = stringResource(id = R.string.error_dialog_icon)
                    )
                },
                confirmButton = {
                    Text(
                        text = stringResource(id = R.string.error_dialog_confirm_button),
                        modifier = Modifier.clickable(onClick = close),
                        style = TutorsScheduleTheme.typography.dialogButtonText
                    )
                },
                title = {
                    Text(
                        text = stringResource(id = R.string.error_dialog_title),
                        style = TutorsScheduleTheme.typography.dialogTitle
                    )
                },
                text = {
                    Text(
                        text = errorMessage,
                        style = TutorsScheduleTheme.typography.dialogText
                    )
                }
            )
        }
    }
}

