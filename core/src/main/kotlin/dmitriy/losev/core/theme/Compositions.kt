package dmitriy.losev.core.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

val LocalTutorsScheduleColors = staticCompositionLocalOf {
    TutorsScheduleColors(
        backgroundPrimary = Color.Unspecified,
        backgroundSecondary = Color.Unspecified,
        button = Color.Unspecified,
        detachedText = Color.Unspecified,
        buttonShadow = Color.Unspecified,
        buttonText = Color.Unspecified,
        hints = Color.Unspecified,
        textField = Color.Unspecified,
        textFiledText = Color.Unspecified,
        textFieldPlaceholder = Color.Unspecified
    )
}

val LocalTutorsScheduleTypography = staticCompositionLocalOf {
    TutorsScheduleTypography(
        mainTitle = TextStyle.Default,
        button = TextStyle.Default,
        textFieldText = TextStyle.Default,
        textFieldPlaceholder = TextStyle.Default,
        hints = TextStyle.Default
    )
}

val LocalTutorsScheduleElevation = staticCompositionLocalOf {
    TutorsScheduleElevation(
        buttonShadow = Dp.Unspecified
    )
}

val LocalTutorsScheduleShapes = staticCompositionLocalOf {
    TutorsScheduleShapes(
        button = RectangleShape,
        textField = RectangleShape
    )
}

