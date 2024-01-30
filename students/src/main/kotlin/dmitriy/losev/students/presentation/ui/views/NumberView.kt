package dmitriy.losev.students.presentation.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dmitriy.losev.ui.theme.LocalTutorsScheduleColors
import dmitriy.losev.ui.views.StudentsNumberText

@Composable
fun NumberView(position: Int) {

    val iconBackgroundColor = LocalTutorsScheduleColors.current.backgroundIconPrimary

    Box(
        modifier = Modifier
            .size(size = 24.dp)
            .background(color = iconBackgroundColor, shape = RoundedCornerShape(size = 4.dp)),
        contentAlignment = Alignment.Center
    ) {
        StudentsNumberText(text = position.toString())
    }

}