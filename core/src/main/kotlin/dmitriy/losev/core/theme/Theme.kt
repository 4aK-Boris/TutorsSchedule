package dmitriy.losev.core.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val darkColors = TutorsScheduleColors(
    backgroundPrimary = Blue100,
    backgroundSecondary = Red100,
    button = White,
    detachedText = White,
    buttonShadow = Black,
    buttonText = Black,
    hints = PurpleGrey40,
    textField = White,
    textFiledText = Black,
    textFieldPlaceholder = PurpleGrey40
)

private val lightColors = TutorsScheduleColors(
    backgroundPrimary = Blue100,
    backgroundSecondary = Red100,
    button = White,
    detachedText = White,
    buttonShadow = Black,
    buttonText = Black,
    hints = PurpleGrey40,
    textField = White,
    textFiledText = Black,
    textFieldPlaceholder = PurpleGrey40
)

@Composable
fun TutorsScheduleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = when {
        darkTheme -> darkColors
        else -> lightColors
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.backgroundPrimary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    CompositionLocalProvider(
        LocalTutorsScheduleColors provides colors,
        LocalTutorsScheduleTypography provides tutorsScheduleTypography,
        LocalTutorsScheduleElevation provides tutorsScheduleElevation,
        LocalTutorsScheduleShapes provides tutorsScheduleShapes,
        content = content
    )
}