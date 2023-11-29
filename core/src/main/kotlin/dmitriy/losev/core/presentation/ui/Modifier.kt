package dmitriy.losev.core.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import dmitriy.losev.core.theme.TutorsScheduleTheme

fun Modifier.tutorsScheduleBackground(): Modifier = composed {
    background(
        brush = Brush.verticalGradient(
            colors = listOf(
                TutorsScheduleTheme.colors.backgroundPrimary,
                TutorsScheduleTheme.colors.backgroundSecondary,
            )
        )
    )
}