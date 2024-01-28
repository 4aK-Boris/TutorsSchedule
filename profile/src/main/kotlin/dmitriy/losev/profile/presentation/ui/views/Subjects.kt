package dmitriy.losev.profile.presentation.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dmitriy.losev.core.models.Subject
import dmitriy.losev.profile.R
import dmitriy.losev.ui.theme.LocalTutorsScheduleColors
import dmitriy.losev.ui.views.Title1ForWidgetText

@Composable
internal fun Subjects(subjects: List<Subject>) {

    val backgroundColor = LocalTutorsScheduleColors.current.subjectsBackground

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(color = backgroundColor, shape = RoundedCornerShape(size = 16.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (subjects.isEmpty()) {
                Title1ForWidgetText(text = stringResource(id = R.string.subject_is_not_detected))
            } else {
                subjects.forEach { subject ->
                    SubjectCard(subject = subject)
                }
            }
        }
    }
}