package dmitriy.losev.ui.views.popups

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import dmitriy.losev.ui.theme.LocalTutorsScheduleColors
import dmitriy.losev.ui.theme.LocalTutorsScheduleShapes
import dmitriy.losev.ui.theme.colors.PopUpBlack
import dmitriy.losev.ui.views.HorizontalSpacer
import dmitriy.losev.ui.views.Title2Text
import dmitriy.losev.ui.views.buttons.CloseIconButton

@Composable
fun DefaultPopUp(visible: Boolean, modifier: Modifier = Modifier, close: () -> Unit, content: @Composable ColumnScope.() -> Unit) {

    val shape = LocalTutorsScheduleShapes.current.popUpShape
    val backgroundColor = LocalTutorsScheduleColors.current.popUpBackground

    val alpha = if (visible) 0.7f else 0f

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = PopUpBlack.copy(alpha = alpha)),
        contentAlignment = Alignment.BottomCenter
    ) {

        if (visible) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(onClick = close, role = Role.Button)
            )
        }

        AnimatedVisibility(
            visible = visible,
            modifier = Modifier.fillMaxWidth(),
            label = "PopUp",
            enter = slideInVertically(
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                initialOffsetY = { height -> height }
            ),
            exit = slideOutVertically(
                animationSpec = spring(stiffness = Spring.StiffnessLow),
                targetOffsetY = { height -> height }
            )
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = shape)
                    .background(color = backgroundColor),
                contentAlignment = Alignment.Center
            ) {

                CloseIconButton(
                    modifier = Modifier
                        .padding(top = 10.dp, end = 16.dp)
                        .align(alignment = Alignment.TopEnd),
                    onClick = close
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 28.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    content()
                }
            }
        }
    }
}

@Composable
fun PopUpTextWithIconButton(modifier: Modifier = Modifier, icon: Painter, text: String, onClick: () -> Unit) {

    val iconColor = LocalTutorsScheduleColors.current.iconPrimary

    Row(
        modifier = modifier.clickable(onClick = onClick, role = Role.Button),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Icon(modifier = Modifier.size(size = 24.dp), painter = icon, contentDescription = text, tint = iconColor)

        HorizontalSpacer(width = 8.dp)

        Title2Text(text = text)
    }
}