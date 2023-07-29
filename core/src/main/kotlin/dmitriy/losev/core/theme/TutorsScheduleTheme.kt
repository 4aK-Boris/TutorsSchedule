package dmitriy.losev.core.theme

import androidx.compose.runtime.Composable

object TutorsScheduleTheme {

    val colors: TutorsScheduleColors
        @Composable
        get() = LocalTutorsScheduleColors.current
    val typography: TutorsScheduleTypography
        @Composable
        get() = LocalTutorsScheduleTypography.current
    val elevation: TutorsScheduleElevation
        @Composable
        get() = LocalTutorsScheduleElevation.current

    val shapes: TutorsScheduleShapes
        @Composable
        get() = LocalTutorsScheduleShapes.current
}