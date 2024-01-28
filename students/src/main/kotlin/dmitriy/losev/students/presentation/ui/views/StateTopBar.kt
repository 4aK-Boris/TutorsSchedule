package dmitriy.losev.students.presentation.ui.views

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import dmitriy.losev.students.core.ScreenState
import dmitriy.losev.ui.theme.LocalTutorsScheduleColors
import dmitriy.losev.ui.theme.LocalTutorsScheduleTypography
import dmitriy.losev.ui.theme.colors.MainBlue

@Composable
fun StateTopBar(screenState: ScreenState, onScreenStateChanged: (ScreenState) -> Unit) {

    val backgroundColor = LocalTutorsScheduleColors.current.backgroundTitle
    val tabWidth = LocalConfiguration.current.screenWidthDp.dp / 2

    val offset by animateDpAsState(
        targetValue = if (screenState == ScreenState.STUDENTS) 0.dp else tabWidth,
        animationSpec = spring(),
        label = "offset"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 40.dp)
            .background(color = backgroundColor)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            ScreenState.entries.forEach { state ->
                Tab(title = state.title, isChecked = state == screenState) {
                    onScreenStateChanged(state)
                }
            }

        }

        Spacer(
            modifier = Modifier
                .width(width = tabWidth)
                .offset(x = offset)
                .height(height = 2.dp)
                .align(alignment = Alignment.BottomStart)
                .background(color = MainBlue)
        )
    }
}

@Composable
private fun Tab(title: String, isChecked: Boolean, onClick: () -> Unit) {

    val tabWidth = LocalConfiguration.current.screenWidthDp.dp / 2

    val colors = LocalTutorsScheduleColors.current

    val textPrimaryColor = colors.textPrimary
    val textHintColor = colors.textHint

    val textColor by animateColorAsState(
        targetValue = if (isChecked) textPrimaryColor else textHintColor,
        label = "textColor",
        animationSpec = spring()
    )

    val textStyle = LocalTutorsScheduleTypography.current.title2

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(width = tabWidth)
            .clickable(onClick = onClick, role = Role.Tab),
        contentAlignment = Alignment.Center
    ) {
        Text(text = title, style = textStyle, color = textColor)
    }
}