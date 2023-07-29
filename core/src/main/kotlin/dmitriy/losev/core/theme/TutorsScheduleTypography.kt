package dmitriy.losev.core.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle

@Immutable
data class TutorsScheduleTypography(
    val mainTitle: TextStyle,
    val button: TextStyle,
    val textFieldText: TextStyle,
    val textFieldPlaceholder: TextStyle,
    val hints: TextStyle
)
