package dmitriy.losev.subjects.presentation.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dmitriy.losev.core.models.Subject
import dmitriy.losev.subjects.R
import dmitriy.losev.ui.theme.LocalTutorsScheduleColors
import dmitriy.losev.ui.theme.LocalTutorsScheduleTypography
import dmitriy.losev.ui.views.HorizontalSpacer
import dmitriy.losev.ui.views.Title1ForWidgetText
import dmitriy.losev.ui.views.Title2ForWidgetText
import dmitriy.losev.ui.views.buttons.MoreIconButton

@Composable
fun SubjectCard(index: Int, subject: Subject, openSubjectPopUp: () -> Unit) {

    val colors = LocalTutorsScheduleColors.current
    val typography = LocalTutorsScheduleTypography.current

    val backgroundIconColor = colors.backgroundIconPrimary
    val iconColor = colors.iconPrimary

    val lesson = typography.lesson

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val priceText = if (subject.price.isNotBlank()) {
        subject.price.let { "Стоимость ${subject.price} ₽" }
    } else {
        stringResource(id = R.string.have_not_price)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Box(
                modifier = Modifier
                    .size(size = 24.dp)
                    .background(color = backgroundIconColor, shape = RoundedCornerShape(size = 5.dp)),
                contentAlignment = Alignment.Center
            ) {

                Text(text = index.toString(), style = lesson, color = iconColor)
            }

            HorizontalSpacer(width = 8.dp)

            Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start) {

                Title1ForWidgetText(text = subject.name, modifier = Modifier.width(width = screenWidth - 96.dp))

                Title2ForWidgetText(text = priceText)
            }
        }

        MoreIconButton(onClick = openSubjectPopUp)
    }
}