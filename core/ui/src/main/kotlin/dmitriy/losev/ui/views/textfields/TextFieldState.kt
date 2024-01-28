package dmitriy.losev.ui.views.textfields

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import dmitriy.losev.ui.R
import dmitriy.losev.ui.theme.LocalTutorsScheduleColors
import dmitriy.losev.ui.theme.colors.Green
import dmitriy.losev.ui.theme.colors.Red
import dmitriy.losev.ui.theme.colors.Yellow

enum class TextFieldState {

    DEFAULT {

        override val unfocusedBorderColor: Color
            @Composable get() = LocalTutorsScheduleColors.current.textFieldBorder

        override val focusedBorderColor: Color
            @Composable get() = LocalTutorsScheduleColors.current.textFieldFocusedBorder

        override val rightIcon: @Composable (() -> Unit)? = null
    },
    ERROR {

        override val unfocusedBorderColor: Color
            @Composable get() = Red

        override val focusedBorderColor: Color
            @Composable get() = Red

        override val rightIcon: @Composable (() -> Unit) = {
            Icon(painter = painterResource(id = R.drawable.error_critical), contentDescription = stringResource(id = R.string.error), tint = Color.Unspecified)
        }
    },
    WARNING {

        override val unfocusedBorderColor: Color
            @Composable get() = Yellow

        override val focusedBorderColor: Color
            @Composable get() = Yellow

        override val rightIcon: @Composable (() -> Unit) = {
            Icon(painter = painterResource(id = R.drawable.warning), contentDescription = stringResource(id = R.string.warning), tint = Color.Unspecified)
        }
    },
    OK {

        override val unfocusedBorderColor: Color
            @Composable get() = Green

        override val focusedBorderColor: Color
            @Composable get() = Green

        override val rightIcon: @Composable (() -> Unit) = {
            Icon(painter = painterResource(id = R.drawable.ok), contentDescription = stringResource(id = R.string.ok), tint = Color.Unspecified)
        }
    };

    @get:Composable
    abstract val unfocusedBorderColor: Color

    @get:Composable
    abstract val focusedBorderColor: Color

    abstract val rightIcon: @Composable (() -> Unit)?
}