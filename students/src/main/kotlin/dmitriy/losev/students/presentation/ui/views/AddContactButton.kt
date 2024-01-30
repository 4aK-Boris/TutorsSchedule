package dmitriy.losev.students.presentation.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dmitriy.losev.ui.theme.LocalTutorsScheduleColors
import dmitriy.losev.ui.views.Title2Text
import dmitriy.losev.ui.views.buttons.FloatingAddIconButton

@Composable
internal fun AddButton(title: String, onClick: () -> Unit) {

    val backgroundColor = LocalTutorsScheduleColors.current.backgroundPrimary

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 48.dp)
            .background(color = backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Title2Text(text = title, modifier = Modifier.align(alignment = Alignment.CenterStart))

            FloatingAddIconButton(onClick = onClick, modifier = Modifier.align(alignment = Alignment.CenterEnd))
        }
    }
}