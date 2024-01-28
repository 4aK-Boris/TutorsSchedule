package dmitriy.losev.ui.views.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dmitriy.losev.ui.core.BUTTON_TEXT
import dmitriy.losev.ui.theme.LocalTutorsScheduleColors
import dmitriy.losev.ui.theme.LocalTutorsScheduleShapes
import dmitriy.losev.ui.theme.LocalTutorsScheduleTypography
import dmitriy.losev.ui.theme.TutorsScheduleTheme

@Composable
fun PrimaryButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {

    val backgroundColor = LocalTutorsScheduleColors.current.primaryButtonBackground
    val textColor = LocalTutorsScheduleColors.current.primaryButtonText

    DefaultButton(modifier = modifier, text = text, background = backgroundColor, textColor = textColor, onClick = onClick)
}

@Composable
fun SecondaryButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {

    val backgroundColor = LocalTutorsScheduleColors.current.secondaryButtonBackground
    val textColor = LocalTutorsScheduleColors.current.secondaryButtonText

    DefaultButton(modifier = modifier, text = text, background = backgroundColor, textColor = textColor, onClick = onClick)
}

@Composable
fun HintButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {

    val textColor = LocalTutorsScheduleColors.current.hintButtonText
    val buttonTextStyle = LocalTutorsScheduleTypography.current.button

    Text(
        text = text,
        color = textColor,
        style = buttonTextStyle,
        modifier = modifier
            .padding(horizontal = 16.dp)
            .clickable(onClick = onClick)
    )
}

@Composable
fun CriticalButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {

    val textColor = LocalTutorsScheduleColors.current.criticalButtonText
    val backgroundColor = LocalTutorsScheduleColors.current.secondaryButtonBackground

    DefaultButton(modifier = modifier, text = text, background = backgroundColor, textColor = textColor, onClick = onClick)
}

@Composable
private fun DefaultButton(modifier: Modifier, text: String, background: Color, textColor: Color, onClick: () -> Unit) {

    val buttonTextStyle = LocalTutorsScheduleTypography.current.button
    val buttonShape = LocalTutorsScheduleShapes.current.button

    Button(
        onClick = onClick,
        shape = buttonShape,
        colors = ButtonDefaults.buttonColors(containerColor = background),
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(height = 48.dp)
    ) {
        Text(text = text, style = buttonTextStyle, color = textColor)
    }
}

@Preview(group = "buttons")
@Composable
private fun PrimaryButtonPreviewWithLightTheme() {

    TutorsScheduleTheme(darkTheme = false) {
        PrimaryButton(text = BUTTON_TEXT) {

        }
    }
}

@Preview(group = "buttons")
@Composable
private fun PrimaryButtonPreviewWithDarkTheme() {

    TutorsScheduleTheme(darkTheme = true) {
        PrimaryButton(text = BUTTON_TEXT) {

        }
    }
}

@Preview(group = "buttons")
@Composable
private fun SecondaryButtonPreviewWithLightTheme() {

    TutorsScheduleTheme(darkTheme = false) {
        SecondaryButton(text = BUTTON_TEXT) {

        }
    }
}

@Preview(group = "buttons")
@Composable
private fun SecondaryButtonPreviewWithDarkTheme() {

    TutorsScheduleTheme(darkTheme = true) {
        SecondaryButton(text = BUTTON_TEXT) {

        }
    }
}

@Preview(group = "buttons")
@Composable
private fun HintButtonPreviewWithLightTheme() {

    TutorsScheduleTheme(darkTheme = false) {
        HintButton(text = BUTTON_TEXT) {

        }
    }
}

@Preview(group = "buttons")
@Composable
private fun HintButtonPreviewWithDarkTheme() {

    TutorsScheduleTheme(darkTheme = true) {
        HintButton(text = BUTTON_TEXT) {

        }
    }
}

@Preview(group = "buttons")
@Composable
private fun CriticalButtonPreviewWithLightTheme() {

    TutorsScheduleTheme(darkTheme = false) {
        CriticalButton(text = BUTTON_TEXT) {

        }
    }
}

@Preview(group = "buttons")
@Composable
private fun CriticalButtonPreviewWithDarkTheme() {

    TutorsScheduleTheme(darkTheme = true) {
        CriticalButton(text = BUTTON_TEXT) {

        }
    }
}