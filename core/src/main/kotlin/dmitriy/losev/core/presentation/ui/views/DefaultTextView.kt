package dmitriy.losev.core.presentation.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dmitriy.losev.core.theme.TutorsScheduleTheme

@Composable
fun DefaultTextView(
    text: String,
    icon: ImageVector,
    contentDescription: String
) {
    Box(
        modifier = Modifier
            .width(width = 300.dp)
            .height(height = 56.dp)
            .shadow(elevation = TutorsScheduleTheme.elevation.buttonShadow, spotColor = Color.Blue, shape = TutorsScheduleTheme.shapes.button, clip = false)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(color = TutorsScheduleTheme.colors.textField, shape = TutorsScheduleTheme.shapes.textField),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Spacer(modifier = Modifier.width(width = 12.dp))
            Icon(imageVector = icon, contentDescription = contentDescription, modifier = Modifier.size(size = 24.dp))
            Spacer(modifier = Modifier.width(width = 12.dp))
            Text(text = text, style = TutorsScheduleTheme.typography.textFieldText, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Start)
            Spacer(modifier = Modifier.width(width = 12.dp))
        }
    }
}

@Composable
@Preview(showBackground = false)
fun DefaultTextViewPreview() {
    DefaultTextView(
        text = "Дмитрий Лосев",
        icon = Icons.Default.Person,
        contentDescription = "Имя и Фамилия"
    )
}