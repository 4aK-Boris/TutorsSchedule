package dmitriy.losev.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dmitriy.losev.ui.core.EMAIL_FIELD_HINT
import dmitriy.losev.ui.core.EMAIL_FIELD_TEXT
import dmitriy.losev.ui.core.EMAIL_FIELD_TITLE
import dmitriy.losev.ui.core.EMPTY_STRING
import dmitriy.losev.ui.theme.LocalTutorsScheduleColors
import dmitriy.losev.ui.theme.LocalTutorsScheduleShapes
import dmitriy.losev.ui.theme.LocalTutorsScheduleTypography
import dmitriy.losev.ui.theme.TutorsScheduleTheme
import dmitriy.losev.ui.views.buttons.SecondaryEditIconButton

@Composable
fun Title1Text(text: String, modifier: Modifier = Modifier) {
    val style = LocalTutorsScheduleTypography.current.title1
    val color = LocalTutorsScheduleColors.current.textPrimary

    Text(text = text, style = style, color = color, modifier = modifier)
}

@Composable
fun Title2Text(modifier: Modifier = Modifier, text: String) {
    val style = LocalTutorsScheduleTypography.current.title2
    val color = LocalTutorsScheduleColors.current.textPrimary

    Text(modifier = modifier, text = text, style = style, color = color, maxLines = 3, overflow = TextOverflow.Ellipsis)
}

@Composable
fun SubTitleText(modifier: Modifier = Modifier, text: String) {
    val style = LocalTutorsScheduleTypography.current.subTitle
    val color = LocalTutorsScheduleColors.current.textSecondary

    Text(modifier = modifier, text = text, style = style, color = color)
}

@Composable
fun Title1ForWidgetText(text: String, modifier: Modifier = Modifier) {
    val style = LocalTutorsScheduleTypography.current.title1ForWidget
    val color = LocalTutorsScheduleColors.current.textPrimary

    Text(text = text, style = style, color = color, modifier = modifier)
}

@Composable
fun Title2ForWidgetText(text: String, modifier: Modifier = Modifier, textAlign: TextAlign = TextAlign.Start) {
    val style = LocalTutorsScheduleTypography.current.title2ForWidget
    val color = LocalTutorsScheduleColors.current.textHint

    Text(text = text, style = style.copy(textAlign = textAlign), color = color, modifier = modifier, overflow = TextOverflow.Ellipsis)
}

@Composable
fun HintText(text: String) {

    val style = LocalTutorsScheduleTypography.current.hint
    val color = LocalTutorsScheduleColors.current.textHint

    Text(text = text, style = style, color = color)
}

@Composable
fun SubBodyText(text: String, modifier: Modifier = Modifier, textAlign: TextAlign = TextAlign.Start) {
    val style = LocalTutorsScheduleTypography.current.subBody
    val color = LocalTutorsScheduleColors.current.textPrimary

    Text(text = text, style = style.copy(textAlign = textAlign), color = color, modifier = modifier)
}

@Composable
fun FilterText(text: String, modifier: Modifier = Modifier, color: Color) {

    val style = LocalTutorsScheduleTypography.current.filter

    Text(text = text, style = style, modifier = modifier, color = color)
}

@Composable
fun StudentsAndGroupText(text: String, modifier: Modifier = Modifier, color: Color) {

    val style = LocalTutorsScheduleTypography.current.studentAndGroupName

    Text(text = text, style = style, modifier = modifier, color = color)
}

@Composable
fun StudentsNumberText(text: String, modifier: Modifier = Modifier) {

    val style = LocalTutorsScheduleTypography.current.studentNumber
    val color = LocalTutorsScheduleColors.current.iconPrimary

    Text(text = text, style = style, modifier = modifier, color = color)
}


@Composable
fun EditableText(title: String, text: String, hint: String, iconEnabled: Boolean = true, enabled: Boolean = true, onClick: () -> Unit) {

    val colors = LocalTutorsScheduleColors.current
    val shapes = LocalTutorsScheduleShapes.current

    val textStyle = LocalTutorsScheduleTypography.current.textField

    val textPrimaryColor = colors.textPrimary
    val textHintColor = colors.textFieldHint
    val backgroundEmptyColor = colors.textFieldEmptyBackground
    val backgroundNotEmptyColor = colors.textFieldBackground
    val borderColor = colors.textFieldBorder

    val borderShape = shapes.textFieldBorder
    val textShape = shapes.textField

    val textInTextField = text.ifBlank { hint }
    val backgroundColor = if (text.isBlank()) backgroundEmptyColor else backgroundNotEmptyColor
    val textColor = if (text.isBlank()) textHintColor else textPrimaryColor

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.SpaceBetween) {

            Title2Text(text = title)

            SecondaryEditIconButton(enabled = iconEnabled, onClick = onClick)
        }

        VerticalSpacer(height = 12.dp)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 50.dp)
                .border(width = 1.dp, color = borderColor, shape = borderShape)
                .clickable(onClick = onClick, role = Role.Button, enabled = enabled),
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .padding(all = 2.dp)
                    .fillMaxSize()
                    .background(color = backgroundColor, shape = textShape)
                    .clickable(onClick = onClick, role = Role.Button, enabled = enabled),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = textInTextField,
                    style = textStyle,
                    color = textColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .clickable(onClick = onClick, role = Role.Button, enabled = enabled)
                )
            }

        }
    }
}

@Preview
@Composable
private fun EditableTextPreviewWithEmptyDataAndLightTheme() {

    TutorsScheduleTheme(darkTheme = false) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 200.dp), contentAlignment = Alignment.Center
        ) {

            EditableText(title = EMAIL_FIELD_TITLE, hint = EMAIL_FIELD_HINT, text = EMPTY_STRING) {

            }
        }
    }
}

@Preview
@Composable
private fun EditableTextPreviewWithDataAndLightTheme() {

    TutorsScheduleTheme(darkTheme = false) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 200.dp), contentAlignment = Alignment.Center
        ) {

            EditableText(title = EMAIL_FIELD_TITLE, hint = EMAIL_FIELD_HINT, text = EMAIL_FIELD_TEXT) {

            }
        }
    }
}

@Preview
@Composable
private fun EditableTextPreviewWithEmptyDataAndDarkTheme() {

    TutorsScheduleTheme(darkTheme = true) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 200.dp), contentAlignment = Alignment.Center
        ) {

            EditableText(title = EMAIL_FIELD_TITLE, hint = EMAIL_FIELD_HINT, text = EMPTY_STRING) {

            }
        }
    }
}

@Preview
@Composable
private fun EditableTextPreviewWithDataAndDarkTheme() {

    TutorsScheduleTheme(darkTheme = true) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 200.dp), contentAlignment = Alignment.Center
        ) {

            EditableText(title = EMAIL_FIELD_TITLE, hint = EMAIL_FIELD_HINT, text = EMAIL_FIELD_TEXT) {

            }
        }
    }
}
