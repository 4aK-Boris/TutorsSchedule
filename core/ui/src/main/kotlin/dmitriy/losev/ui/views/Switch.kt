package dmitriy.losev.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dmitriy.losev.ui.theme.LocalTutorsScheduleColors
import dmitriy.losev.ui.theme.TutorsScheduleTheme

@Composable
fun DefaultSwitch(state: Boolean, onStateChanged: (Boolean) -> Unit) {

    val colors = LocalTutorsScheduleColors.current

    val backgroundUnselectedColor = colors.backgroundSwitch
    val backgroundSelectedColor = colors.backgroundSelectedSwitch
    val switchUnselectedColor = colors.switch
    val switchSelectedColor = colors.selectedSwitch

    Switch(
        checked = state,
        onCheckedChange = onStateChanged,
        thumbContent = {
            Box(modifier = Modifier.size(size = 24.dp))
        },
        colors = SwitchDefaults.colors(
            uncheckedBorderColor = Color.Transparent,
            checkedBorderColor = Color.Transparent,
            disabledCheckedBorderColor = Color.Transparent,
            disabledUncheckedBorderColor = Color.Transparent,
            checkedIconColor = switchSelectedColor,
            uncheckedTrackColor = backgroundUnselectedColor,
            checkedTrackColor = backgroundSelectedColor,
            uncheckedIconColor = switchUnselectedColor,
            checkedThumbColor = switchSelectedColor,
            uncheckedThumbColor = switchUnselectedColor
        )
    )
}

@Preview
@Composable
private fun DefaultSwitchPreviewWithLightTheme() {

    val (state, onStateChanged) = remember { mutableStateOf(value = false) }

    TutorsScheduleTheme(darkTheme = false) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            DefaultSwitch(state = state, onStateChanged = onStateChanged)
        }
    }
}

@Preview
@Composable
private fun DefaultSwitchPreviewWithDarkTheme() {

    val (state, onStateChanged) = remember { mutableStateOf(value = false) }

    TutorsScheduleTheme(darkTheme = true) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            DefaultSwitch(state = state, onStateChanged = onStateChanged)
        }
    }
}