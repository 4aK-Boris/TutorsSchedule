package dmitriy.losev.ui.views.menu

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dmitriy.losev.ui.R
import dmitriy.losev.ui.theme.LocalTutorsScheduleColors
import dmitriy.losev.ui.theme.LocalTutorsScheduleTypography
import dmitriy.losev.ui.theme.TutorsScheduleTheme
import dmitriy.losev.ui.views.VerticalSpacer

@Composable
fun Menu(
    selectedItem: MenuItem,
    onProfileMenuItemClicked: () -> Unit,
    onCalendarMenuItemClicked: () -> Unit,
    onFinanceMenuItemClicked: () -> Unit,
    onStudentsMenuItemClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        MenuItem(
            icon = painterResource(id = R.drawable.profile),
            text = stringResource(id = R.string.profile),
            selected = selectedItem == MenuItem.PROFILE,
            onClick = onProfileMenuItemClicked
        )

        MenuItem(
            icon = painterResource(id = R.drawable.calendar),
            text = stringResource(id = R.string.calendar),
            selected = selectedItem == MenuItem.CALENDAR,
            onClick = onCalendarMenuItemClicked
        )

        MenuItem(
            icon = painterResource(id = R.drawable.finance),
            text = stringResource(id = R.string.finance),
            selected = selectedItem == MenuItem.FINANCE,
            onClick = onFinanceMenuItemClicked
        )

        MenuItem(
            icon = painterResource(id = R.drawable.students),
            text = stringResource(id = R.string.students),
            selected = selectedItem == MenuItem.STUDENTS,
            onClick = onStudentsMenuItemClicked
        )
    }
}

@Composable
private fun MenuItem(icon: Painter, text: String, selected: Boolean, onClick: () -> Unit) {

    val width = LocalConfiguration.current.screenWidthDp.dp / 4

    val colors = LocalTutorsScheduleColors.current

    val color = colors.menu
    val unselectedBackgroundColor = colors.menuBackground
    val selectedBackgroundColor = colors.menuSelectedBackground

    val textStyle = LocalTutorsScheduleTypography.current.menu

    val backgroundColor by animateColorAsState(
        targetValue = if (selected) selectedBackgroundColor else unselectedBackgroundColor,
        label = "backgroundColor",
        animationSpec = spring()
    )

    Column(
        modifier = Modifier
            .height(height = 60.dp)
            .width(width = width)
            .clickable(onClick = onClick)
            .background(color = backgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(painter = icon, contentDescription = text, modifier = Modifier.size(size = 24.dp), tint = Color.Unspecified)

        VerticalSpacer(height = 6.dp)

        Text(text = text, style = textStyle, color = color)
    }
}

@Preview
@Composable
private fun MenuPreviewWithLightTheme() {

    val (item, onItemChanged) = remember { mutableStateOf(value = MenuItem.PROFILE) }

    TutorsScheduleTheme(darkTheme = false) {
        Menu(
            selectedItem = item,
            onProfileMenuItemClicked = { onItemChanged(MenuItem.PROFILE) },
            onCalendarMenuItemClicked = { onItemChanged(MenuItem.CALENDAR) },
            onFinanceMenuItemClicked = { onItemChanged(MenuItem.FINANCE) },
            onStudentsMenuItemClicked = { onItemChanged(MenuItem.STUDENTS) }
        )
    }
}

@Preview
@Composable
private fun MenuPreviewWithDarkTheme() {

    val (item, onItemChanged) = remember { mutableStateOf(value = MenuItem.PROFILE) }

    TutorsScheduleTheme(darkTheme = true) {
        Menu(
            selectedItem = item,
            onProfileMenuItemClicked = { onItemChanged(MenuItem.PROFILE) },
            onCalendarMenuItemClicked = { onItemChanged(MenuItem.CALENDAR) },
            onFinanceMenuItemClicked = { onItemChanged(MenuItem.FINANCE) },
            onStudentsMenuItemClicked = { onItemChanged(MenuItem.STUDENTS) }
        )
    }
}