package dmitriy.losev.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import dmitriy.losev.ui.theme.colors.TutorsScheduleColors
import dmitriy.losev.ui.theme.shapes.TutorsScheduleShapes
import dmitriy.losev.ui.theme.typography.TutorsScheduleTypography

val LocalTutorsScheduleColors = staticCompositionLocalOf {
    TutorsScheduleColors(
        backgroundPrimary = Color.Unspecified,
        backgroundSecondary = Color.Unspecified,
        backgroundTitle = Color.Unspecified,
        iconPrimary = Color.Unspecified,
        iconSecondary = Color.Unspecified,
        backgroundIconPrimary = Color.Unspecified,
        backgroundIconSecondary = Color.Unspecified,
        textPrimary = Color.Unspecified,
        textSecondary = Color.Unspecified,
        textHint = Color.Unspecified,
        backgroundSwitch = Color.Unspecified,
        backgroundSelectedSwitch = Color.Unspecified,
        switch = Color.Unspecified,
        selectedSwitch = Color.Unspecified,
        textFieldEmptyBackground = Color.Unspecified,
        textFieldBackground = Color.Unspecified,
        textFieldBorder = Color.Unspecified,
        textFieldFocusedBorder = Color.Unspecified,
        textFieldCursor = Color.Unspecified,
        textFieldHint = Color.Unspecified,
        primaryButtonBackground = Color.Unspecified,
        secondaryButtonBackground = Color.Unspecified,
        primaryButtonText = Color.Unspecified,
        secondaryButtonText = Color.Unspecified,
        hintButtonText = Color.Unspecified,
        criticalButtonText = Color.Unspecified,
        subjectsBackground = Color.Unspecified,
        popUpBackground = Color.Unspecified,
        menuBackground = Color.Unspecified,
        menuSelectedBackground = Color.Unspecified,
        menu = Color.Unspecified,
        statusBar = Color.Unspecified,
        filterPrimary = Color.Unspecified,
        filterSecondary = Color.Unspecified,
        filter = Color.Unspecified,
        filterTextColor = Color.Unspecified
    )
}

val LocalTutorsScheduleTypography = staticCompositionLocalOf {
    TutorsScheduleTypography(
        title1ForWidget = TextStyle.Default,
        title2ForWidget = TextStyle.Default,
        title1 = TextStyle.Default,
        title2 = TextStyle.Default,
        subTitle = TextStyle.Default,
        textField = TextStyle.Default,
        textFieldFilter = TextStyle.Default,
        button = TextStyle.Default,
        text1 = TextStyle.Default,
        filter = TextStyle.Default,
        studentNumber = TextStyle.Default,
        studentAndGroupName = TextStyle.Default,

        popUpTitle = TextStyle.Default,
        popUpText = TextStyle.Default,
        logo = TextStyle.Default,
        hint = TextStyle.Default,
        oldTitle = TextStyle.Default,
        menu = TextStyle.Default,
        oldSubtitle = TextStyle.Default,
        body = TextStyle.Default,
        subBody = TextStyle.Default,
        lesson = TextStyle.Default
    )
}

val LocalTutorsScheduleShapes = staticCompositionLocalOf {
    TutorsScheduleShapes(
        button = RectangleShape,
        textField = RectangleShape,
        textFieldBorder = RectangleShape,
        smallShape = RectangleShape,
        popUpShape = RectangleShape,
        avatar = RectangleShape
    )
}

