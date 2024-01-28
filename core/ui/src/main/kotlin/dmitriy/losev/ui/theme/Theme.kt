package dmitriy.losev.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import dmitriy.losev.ui.theme.colors.darkColors
import dmitriy.losev.ui.theme.colors.lightColors
import dmitriy.losev.ui.theme.shapes.tutorsScheduleShapes
import dmitriy.losev.ui.theme.typography.tutorsScheduleTypography


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
            window.statusBarColor = colors.statusBar.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    CompositionLocalProvider(
        LocalTutorsScheduleColors provides colors,
        LocalTutorsScheduleTypography provides tutorsScheduleTypography,
        LocalTutorsScheduleShapes provides tutorsScheduleShapes,
        content = content
    )
}