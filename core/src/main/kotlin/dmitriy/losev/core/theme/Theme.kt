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
    topBar = Green300,
    button = White,
    detachedText = White,
    buttonShadow = Black,
    buttonText = Black,
    hints = PurpleGrey40,
    textField = White,
    textFiledText = Black,
    textFieldPlaceholder = PurpleGrey40,
    transparent = TransparentDark,
    iconBorder = Gold,
    studentsListPrimary = White,
    studentsListSecondary = Blue800,
    floatingButton = Blue800
)

private val lightColors = TutorsScheduleColors(
    backgroundPrimary = Blue100,
    backgroundSecondary = Red100,
    topBar = Green300,
    button = White,
    detachedText = White,
    buttonShadow = Black,
    buttonText = Black,
    hints = PurpleGrey40,
    textField = White,
    textFiledText = Black,
    textFieldPlaceholder = PurpleGrey40,
    transparent = TransparentLight,
    iconBorder = Gold,
    studentsListPrimary = White,
    studentsListSecondary = Blue800,
    floatingButton = Blue800
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
            window.statusBarColor = colors.topBar.toArgb()
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