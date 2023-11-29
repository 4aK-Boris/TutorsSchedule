@file:JvmName("TutorsScheduleTypographyKt")

package dmitriy.losev.core.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dmitriy.losev.core.R

private val tutorsScheduleFont = Font(R.font.calipsoc)
private val tutorsScheduleFontFamily = FontFamily(tutorsScheduleFont)

val tutorsScheduleTypography = TutorsScheduleTypography(
    mainTitle = TextStyle(
        fontFamily = tutorsScheduleFontFamily,
        fontWeight = FontWeight.W800,
        fontSize = 32.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    dialogTitle = TextStyle(
        fontFamily = tutorsScheduleFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp,
        lineHeight = 24.sp
    ),
    dialogText = TextStyle(
        fontFamily = tutorsScheduleFontFamily,
        fontWeight = FontWeight.W300,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    dialogButtonText = TextStyle(
        fontFamily = tutorsScheduleFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    button = TextStyle(
        fontFamily = tutorsScheduleFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
    ),
    textFieldText = TextStyle(
        fontFamily = tutorsScheduleFontFamily,
        fontWeight = FontWeight.W300,
        fontSize = 18.sp,
        //lineHeight = 24.sp,
        //letterSpacing = 0.5.sp
    ),
    textFieldPlaceholder = TextStyle(
        fontFamily = tutorsScheduleFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    hints = TextStyle(
        fontFamily = tutorsScheduleFontFamily,
        fontWeight = FontWeight.W300,
        fontSize = 14.sp,
    ),
    studentsListPrimary = TextStyle(
        fontFamily = tutorsScheduleFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    studentsListSecondary = TextStyle(
        fontFamily = tutorsScheduleFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        lineHeight = 20.sp
    ),
)