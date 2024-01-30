package dmitriy.losev.students.presentation.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import dmitriy.losev.core.models.StudentName
import dmitriy.losev.ui.theme.LocalTutorsScheduleColors
import dmitriy.losev.ui.views.HorizontalSpacer
import dmitriy.losev.ui.views.StudentsAndGroupText
import dmitriy.losev.ui.views.buttons.CheckIconButton

@Composable
fun StudentChooserView(student: StudentName, isChoose: Boolean, position: Int, onClick: () -> Unit) {

    val textColor = LocalTutorsScheduleColors.current.textPrimary

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 56.dp)
                .clickable(onClick = onClick, role = Role.RadioButton)
        ) {

            Row(
                modifier = Modifier.align(alignment = Alignment.CenterStart),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                NumberView(position = position)

                HorizontalSpacer(width = 8.dp)

                StudentsAndGroupText(text = student.name, color = textColor)
            }

            if (isChoose) {

                CheckIconButton(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .align(alignment = Alignment.CenterEnd),
                    onClick = onClick
                )
            }
        }
    }

}