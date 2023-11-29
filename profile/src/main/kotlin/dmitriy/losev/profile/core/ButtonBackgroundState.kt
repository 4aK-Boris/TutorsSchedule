package dmitriy.losev.profile.core

import androidx.compose.ui.graphics.Color
import dmitriy.losev.core.theme.Green
import dmitriy.losev.core.theme.Red
import dmitriy.losev.core.theme.White

enum class ButtonBackgroundState(val color: Color) {
    DEFAULT(color = White),
    ERROR(color = Red),
    SUCCESS(color = Green);
}