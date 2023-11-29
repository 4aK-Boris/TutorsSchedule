package dmitriy.losev.profile.presentation.ui.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import dmitriy.losev.core.presentation.ui.views.DefaultTextFieldWithTwoIcon
import dmitriy.losev.core.theme.Red
import dmitriy.losev.profile.R
import dmitriy.losev.profile.core.ButtonState

@Composable
fun EmailTextFieldView(
    email: String,
    onEmailChanged: (String) -> Unit,
    isEmailChanged: Boolean,
    buttonState: ButtonState,
    hasNeedEmailVerified: Boolean,
    onNotVerifiedEmailClick: () -> Unit,
    onClick: () -> Unit
) {

    val iconColor by animateColorAsState(
        targetValue = buttonState.color,
        animationSpec = spring(),
        label = "iconColor"
    )

    DefaultTextFieldWithTwoIcon(
        text = email,
        onTextChanged = onEmailChanged,
        icon = Icons.Default.Email,
        contentDescription = stringResource(id = R.string.email),
        placeHolder = stringResource(id = R.string.email),
        enabled = !hasNeedEmailVerified,
        keyboardType = KeyboardType.Email,
    ) {
        if (hasNeedEmailVerified) {
            IconButton(onClick = onNotVerifiedEmailClick) {
                Icon(
                    imageVector = Icons.Default.ErrorOutline,
                    contentDescription = stringResource(id = R.string.email_is_not_verified),
                    tint = Red
                )
            }
        } else {
            AnimatedVisibility(
                visible = isEmailChanged,
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
}