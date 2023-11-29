package dmitriy.losev.auth.presentation.ui.views

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dmitriy.losev.auth.R
import dmitriy.losev.core.theme.TutorsScheduleTheme

@Composable
fun AuthenticationButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(height = 48.dp)
            .width(width = 256.dp)
            .shadow(
                elevation = TutorsScheduleTheme.elevation.buttonShadow,
                ambientColor = TutorsScheduleTheme.colors.buttonShadow,
                spotColor = TutorsScheduleTheme.colors.buttonShadow,
                shape = TutorsScheduleTheme.shapes.button
            ),
        colors = ButtonDefaults.buttonColors(containerColor = TutorsScheduleTheme.colors.button),
        shape = TutorsScheduleTheme.shapes.button
    ) {
        Text(
            text = text,
            style = TutorsScheduleTheme.typography.button,
            color = TutorsScheduleTheme.colors.buttonText
        )
    }
}

@Composable
fun AuthenticationGoogleButton(modifier: Modifier = Modifier, onClick: () -> Unit) {

    dmitriy.losev.core.presentation.ui.views.AuthenticationButton(
        modifier = modifier,
        text = stringResource(id = R.string.google_login),
        painter = painterResource(id = R.drawable.google_icon),
        onClick = onClick
    )
}