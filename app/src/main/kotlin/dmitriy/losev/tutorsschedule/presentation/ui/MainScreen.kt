package dmitriy.losev.tutorsschedule.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.auth.api.identity.SignInClient
import dmitriy.losev.tutorsschedule.presentation.navigation.Navigation

@Composable
fun MainScreen(modifier: Modifier, client: SignInClient) {
    Navigation(modifier = modifier, client = client)
}