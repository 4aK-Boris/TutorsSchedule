package dmitriy.losev.auth.presentation.ui.views

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dmitriy.losev.auth.R
import dmitriy.losev.core.theme.PurpleGrey40
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
fun AuthenticationGoogleButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {

    Row(
        modifier = modifier
            .height(height = 48.dp)
            .width(width = 256.dp)
            .shadow(
                elevation = TutorsScheduleTheme.elevation.buttonShadow,
                ambientColor = TutorsScheduleTheme.colors.buttonShadow,
                spotColor = TutorsScheduleTheme.colors.buttonShadow,
                shape = TutorsScheduleTheme.shapes.button
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        FilledIconButton(
            onClick = onClick,
            shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp),
            modifier = Modifier
                .size(size = 48.dp)
                .border(
                    width = 2.dp,
                    color = PurpleGrey40,
                    shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)
                ),
            colors = IconButtonDefaults.filledIconButtonColors(containerColor = TutorsScheduleTheme.colors.button)
        ) {
            Box(
                modifier = Modifier
                    .size(size = 48.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.google_icon),
                    contentDescription = "Вход с помощью гугл",
                    modifier = Modifier.size(size = 36.dp),
                    tint = Color.Unspecified
                )
            }
        }
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(containerColor = TutorsScheduleTheme.colors.button),
            shape = RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp),
            modifier = Modifier
                .height(height = 48.dp)
                .width(width = 208.dp)
        ) {
            Text(
                text = text,
                style = TutorsScheduleTheme.typography.button,
                color = TutorsScheduleTheme.colors.buttonText
            )
        }
    }
}