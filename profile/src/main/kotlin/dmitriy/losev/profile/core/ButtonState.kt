package dmitriy.losev.profile.core

import androidx.compose.ui.graphics.Color
import dmitriy.losev.core.theme.Gray
import dmitriy.losev.core.theme.Green
import dmitriy.losev.core.theme.Red

enum class ButtonState(val color: Color) {
    DEFAULT(color = Gray),
    ERROR(color = Red),
    SUCCESS(color = Green);
}