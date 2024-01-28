package dmitriy.losev.ui.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import dmitriy.losev.ui.theme.TutorsScheduleTheme
import dmitriy.losev.ui.views.ErrorToast
import org.koin.core.component.KoinComponent

abstract class BaseActivity : ComponentActivity(), KoinComponent {

    abstract val baseViewModel: BaseViewModel

    @Composable
    abstract fun UI(modifier: Modifier)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        baseViewModel.loadThemeState()

        setContent {

            val themeState by baseViewModel.themeState.collectAsState(initial = THEME_DEFAULT_VALUE)

            TutorsScheduleTheme(darkTheme = themeState) {

                Surface(modifier = Modifier.fillMaxSize()) {

                    val errorMessage by baseViewModel.errorMessage.collectAsState()
                    val errorState by baseViewModel.errorState.collectAsState()

                    UI(modifier = Modifier)

                    if (errorState) {
                        ErrorToast(text = errorMessage)
                    }
                }

            }
        }
    }
}

