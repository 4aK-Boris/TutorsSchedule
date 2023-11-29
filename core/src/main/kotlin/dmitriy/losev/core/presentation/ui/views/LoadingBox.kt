package dmitriy.losev.core.presentation.ui.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dmitriy.losev.core.theme.TutorsScheduleTheme

@Composable
fun LoadingBox(isLoading: Boolean, content: @Composable () -> Unit) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        content()

        AnimatedVisibility(visible = isLoading) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(size = 32.dp),
                    strokeWidth = 4.dp,
                    trackColor = TutorsScheduleTheme.colors.backgroundPrimary,
                    color = TutorsScheduleTheme.colors.backgroundSecondary
                )
            }

        }
    }
}