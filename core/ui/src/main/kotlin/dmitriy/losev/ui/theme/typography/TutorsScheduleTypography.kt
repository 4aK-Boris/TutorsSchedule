package dmitriy.losev.ui.theme.typography

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle

@Immutable
data class TutorsScheduleTypography(
    val title1ForWidget: TextStyle,
    val title2ForWidget: TextStyle,
    val title1: TextStyle,
    val title2: TextStyle,
    val subTitle: TextStyle,
    val textField: TextStyle,
    val textFieldFilter: TextStyle,
    val button: TextStyle,
    val text1: TextStyle,
    val filter: TextStyle,
    val studentNumber: TextStyle,
    val studentAndGroupName: TextStyle,

    val popUpTitle: TextStyle,
    val popUpText: TextStyle,
    val logo: TextStyle,
    val hint: TextStyle,
    val oldTitle: TextStyle,
    val menu: TextStyle,
    val oldSubtitle: TextStyle,
    val body: TextStyle,
    val subBody: TextStyle,
    val lesson: TextStyle,
)
