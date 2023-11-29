package dmitriy.losev.core.presentation.ui.views

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import dmitriy.losev.core.theme.PurpleGrey40
import dmitriy.losev.core.theme.TutorsScheduleTheme

@Composable
fun MultiLineTextFieldWithIcon(
    text: String,
    onTextChanged: (String) -> Unit,
    icon: ImageVector,
    contentDescription: String,
    imeAction: ImeAction = ImeAction.Done,
    placeHolder: String,
) {

    val focusManager = LocalFocusManager.current

    val (focus, onFocusChanged) = remember { mutableStateOf(value = false) }

    val borderColor by animateColorAsState(
        targetValue = if (focus) PurpleGrey40 else Color.Unspecified,
        animationSpec = spring(stiffness = Spring.StiffnessMedium),
        label = "animate_text_field_border_color"
    )

    val shadowColor by animateColorAsState(
        targetValue = if (focus) Color.Blue else Color.Black,
        animationSpec = spring(stiffness = Spring.StiffnessMedium),
        label = "animate_text_field_border_color"
    )

    TextField(
        value = text,
        onValueChange = onTextChanged,
        modifier = Modifier
            .requiredWidth(width = 360.dp)
            .requiredHeight(height = 128.dp)
            .padding(horizontal = 20.dp)
            .onFocusChanged { focusState ->
                onFocusChanged(focusState.isFocused)
            }
            .border(
                width = 2.dp,
                shape = TutorsScheduleTheme.shapes.textField,
                color = borderColor
            )
            .shadow(
                elevation = TutorsScheduleTheme.elevation.buttonShadow,
                spotColor = shadowColor,
                shape = TutorsScheduleTheme.shapes.button,
                clip = false
            ),
        keyboardOptions = KeyboardOptions(
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            imeAction = imeAction,
            capitalization = KeyboardCapitalization.Words
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() },
            onNext = { focusManager.moveFocus(focusDirection = FocusDirection.Down) }
        ),
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = contentDescription)
        },
        shape = TutorsScheduleTheme.shapes.textField,
        singleLine = false,
        maxLines = 5,
        placeholder = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart) {
                Text(
                    text = placeHolder,
                    color = TutorsScheduleTheme.colors.textFieldPlaceholder,
                    style = TutorsScheduleTheme.typography.textFieldPlaceholder
                )
            }
        },
        textStyle = TutorsScheduleTheme.typography.textFieldText,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedTextColor = TutorsScheduleTheme.colors.textFiledText,
            disabledTextColor = TutorsScheduleTheme.colors.textFiledText,
            unfocusedTextColor = TutorsScheduleTheme.colors.textFiledText,
            errorTextColor = TutorsScheduleTheme.colors.textFiledText
        )
    )
}