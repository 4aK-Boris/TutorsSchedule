package dmitriy.losev.students.presentation.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import dmitriy.losev.core.models.StudentName
import dmitriy.losev.students.R
import dmitriy.losev.ui.theme.LocalTutorsScheduleColors
import dmitriy.losev.ui.theme.LocalTutorsScheduleShapes
import dmitriy.losev.ui.theme.LocalTutorsScheduleTypography
import dmitriy.losev.ui.views.StudentsAndGroupText
import dmitriy.losev.ui.views.Title2Text
import dmitriy.losev.ui.views.VerticalSpacer
import dmitriy.losev.ui.views.buttons.SecondaryEditIconButton

@Composable
internal fun StudentsView(students: List<StudentName>, onEditClick: () -> Unit, onClick: () -> Unit = onEditClick) {

    val colors = LocalTutorsScheduleColors.current
    val shapes = LocalTutorsScheduleShapes.current

    val textStyle = LocalTutorsScheduleTypography.current.textField

    val textPrimaryColor = colors.textPrimary
    val textHintColor = colors.textFieldHint
    val backgroundEmptyColor = colors.textFieldEmptyBackground
    val backgroundNotEmptyColor = colors.textFieldBackground
    val borderColor = colors.textFieldBorder

    val borderShape = shapes.textFieldBorder
    val textShape = shapes.textField

    val backgroundColor = if (students.isEmpty()) backgroundEmptyColor else backgroundNotEmptyColor

    val enabled = students.isEmpty()
    val iconVisible = students.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.SpaceBetween) {

            Title2Text(text = stringResource(id = R.string.students_title))

            Box(contentAlignment = Alignment.Center) {

                if (iconVisible) {

                    SecondaryEditIconButton(onClick = onEditClick)
                }
            }
        }

        VerticalSpacer(height = 12.dp)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = borderColor, shape = borderShape)
                .clickable(onClick = onClick, role = Role.Button),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(all = 2.dp)
                    .fillMaxWidth()
                    .background(color = backgroundColor, shape = textShape)
                    .padding(horizontal = 20.dp, vertical = 14.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                if (enabled) {
                    Text(
                        text = stringResource(id = R.string.add_students2),
                        style = textStyle,
                        color = textHintColor,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(onClick = onClick, role = Role.Button)
                    )
                } else {
                    students.forEachIndexed { index, student ->

                        StudentsAndGroupText(text = student.name, color = textPrimaryColor)

                        if (index != students.lastIndex) {
                            VerticalSpacer(height = 12.dp)
                        }
                    }
                }
            }
        }
    }
}