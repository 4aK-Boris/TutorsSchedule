package dmitriy.losev.core.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dmitriy.losev.core.R
import dmitriy.losev.core.theme.PurpleGrey80
import dmitriy.losev.core.theme.TutorsScheduleTheme
import org.koin.core.component.KoinComponent

abstract class BaseActivity : ComponentActivity(), KoinComponent {

    abstract val baseViewModel: BaseViewModel

    @Composable
    abstract fun UI(modifier: Modifier)

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TutorsScheduleTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val scaffoldState = rememberBottomSheetScaffoldState()

                    val errorMessage by baseViewModel.errorMessage.collectAsState()
                    val errorState by baseViewModel.errorState.collectAsState()

                    Scaffold(
                        snackbarHost = {
                            SnackbarHost(
                                hostState = scaffoldState.snackbarHostState,
                            ) { data ->
                                Snackbar(
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                                    containerColor = PurpleGrey80,
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = data.visuals.message,
                                            color = Color.Black,
                                            fontSize = 16.sp,
                                            fontFamily = FontFamily.SansSerif,
                                            fontStyle = FontStyle.Italic,
                                            fontWeight = FontWeight.W700,
                                            modifier = Modifier.fillMaxWidth(fraction = 0.7f)
                                        )

                                        Icon(
                                            painter = painterResource(id = R.drawable.error),
                                            contentDescription = "",
                                            modifier = Modifier.size(size = 48.dp),
                                            tint = Color.Red
                                        )
                                    }
                                }
                            }
                        }
                    ) { paddingValues ->
                        UI(modifier = Modifier.padding(paddingValues = paddingValues))
                    }

                    LaunchedEffect(key1 = errorState, key2 = errorMessage) {
                        if (errorState) {
                            scaffoldState.snackbarHostState.showSnackbar(message = errorMessage)
                            baseViewModel.close()
                        }
                    }
                }

            }
        }
    }
}

