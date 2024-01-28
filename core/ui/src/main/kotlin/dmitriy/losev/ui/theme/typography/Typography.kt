@file:JvmName("TutorsScheduleTypographyKt")

package dmitriy.losev.ui.theme.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import dmitriy.losev.ui.R
import dmitriy.losev.ui.theme.colors.Black

private val montserratFont = Font(R.font.montserrat)
private val fredokaFont = Font(R.font.fredoka)

private val montserratFontFamily = FontFamily(montserratFont)
private val fredokaFontFamily = FontFamily(fredokaFont)

internal val toastTextStyle = TextStyle(
    fontFamily = montserratFontFamily,
    fontWeight = FontWeight.W400,
    fontSize = 14.sp,
    lineHeight = 17.sp,
    textAlign = TextAlign.Start,
    color = Black
)

val tutorsScheduleTypography = TutorsScheduleTypography(
    title1ForWidget = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Start,
    ),
    title2ForWidget = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 17.sp,
        textAlign = TextAlign.Start,
    ),
    title1 = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        textAlign = TextAlign.Center,
    ),
    title2 = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Start,
    ),
    subTitle = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 13.sp,
        textAlign = TextAlign.Start,
    ),
    textField = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Start,
        textDecoration = TextDecoration.None
    ),
    textFieldFilter = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 13.sp,
        lineHeight = 17.sp,
        textAlign = TextAlign.Start,
        textDecoration = TextDecoration.None
    ),
    button = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Center
    ),
    text1 = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        textAlign = TextAlign.Start
    ),
    filter = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 13.sp,
        lineHeight = 15.sp,
        textAlign = TextAlign.Center
    ),
    studentNumber = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        textAlign = TextAlign.Center
    ),
    studentAndGroupName = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 17.sp,
        textAlign = TextAlign.Start
    ),

    popUpTitle = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
        lineHeight = 24.sp
    ),
    popUpText = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        lineHeight = 20.sp
    ),
    logo = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        lineHeight = 20.sp
    ),
    hint = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 15.sp,
        textAlign = TextAlign.Center,
        lineHeight = 18.sp
    ),
    oldTitle = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
        lineHeight = 24.sp
    ),
    menu = TextStyle(
        fontFamily = fredokaFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        textAlign = TextAlign.Center,
        lineHeight = 13.sp,
        letterSpacing = 0.32.sp
    ),
    oldSubtitle = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp,
        textAlign = TextAlign.Start,
        lineHeight = 20.sp
    ),
    body = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        textAlign = TextAlign.Start,
        lineHeight = 16.sp
    ),
    subBody = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 13.sp,
        textAlign = TextAlign.Start,
        lineHeight = 13.sp
    ),
    lesson =  TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        textAlign = TextAlign.Center,
    )
)