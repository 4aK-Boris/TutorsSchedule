package dmitriy.losev.profile.presentation.ui.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import dmitriy.losev.core.presentation.ui.views.DefaultTextFieldWithTwoIcon
import dmitriy.losev.profile.R
import dmitriy.losev.profile.core.ButtonState

@Composable
fun ProfileTextFieldView(
    value: String,
    onValueChanged: (String) -> Unit,
    isValueChanged: Boolean,
    icon: ImageVector,
    contentDescription: String,
    buttonState: ButtonState,
    onClick: () -> Unit
) {

    val iconColor by animateColorAsState(
        targetValue = buttonState.color,
        animationSpec = spring(),
        label = "iconColor"
    )

    DefaultTextFieldWithTwoIcon(
        text = value,
        onTextChanged = onValueChanged,
        icon = icon,
        contentDescription = contentDescription,
        placeHolder = contentDescription,
        autoCorrect = true,
        capitalization = KeyboardCapitalization.Words
    ) {
        AnimatedVisibility(
            visible = isValueChanged,
            enter = expandHorizontally(
                animationSpec = spring(),
                expandFrom = Alignment.Start
            ),
            exit = shrinkHorizontally(
                animationSpec = spring(),
                shrinkTowards = Alignment.Start
            )
        ) {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(id = R.string.confirm_changed),
                    tint = iconColor
                )
            }
        }
    }
}