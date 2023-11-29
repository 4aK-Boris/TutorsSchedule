package dmitriy.losev.core.presentation.ui.views

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import dmitriy.losev.core.R
import dmitriy.losev.core.theme.PurpleGrey40
import dmitriy.losev.core.theme.TutorsScheduleTheme

@Composable
fun DefaultPasswordTextField(
    password: String,
    onPasswordChanged: (String) -> Unit,
    imeAction: ImeAction = ImeAction.Done,
    placeHolder: String
) {

    val focusManager = LocalFocusManager.current

    val (focus, onFocusChanged) = remember { mutableStateOf(value = false) }

    var passwordVisibility by remember { mutableStateOf(false) }

    val visibilityIcon = if (passwordVisibility) {
        Icons.Default.Visibility
    } else {
        Icons.Default.VisibilityOff
    }

    val visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()

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
        value = password,
        onValueChange = onPasswordChanged,
        visualTransformation = visualTransformation,
        modifier = Modifier
            .requiredWidth(width = 360.dp)
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
            autoCorrect = false,
            keyboardType = KeyboardType.Password,
            imeAction = imeAction,
            capitalization = KeyboardCapitalization.None
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() },
            onNext = { focusManager.moveFocus(FocusDirection.Down) },
        ),
        trailingIcon = {
            IconButton(onClick = {
                passwordVisibility = !passwordVisibility
            }) {
                Crossfade(targetState = visibilityIcon, label = "password_visibility", animationSpec = spring()) { icon ->
                    Icon(
                        imageVector = icon,
                        contentDescription = stringResource(id = R.string.password_visibility)
                    )
                }
            }
        },
        shape = TutorsScheduleTheme.shapes.textField,
        singleLine = true,
        placeholder = {
            Text(
                text = placeHolder,
                color = TutorsScheduleTheme.colors.textFieldPlaceholder,
                style = TutorsScheduleTheme.typography.textFieldPlaceholder
            )
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