package dmitriy.losev.profile.presentation.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dmitriy.losev.core.models.Subject
import dmitriy.losev.profile.R
import dmitriy.losev.ui.views.Title1ForWidgetText
import dmitriy.losev.ui.views.Title2ForWidgetText
import dmitriy.losev.ui.views.VerticalSpacer

@Composable
fun SubjectCard(subject: Subject) {

    val price = if (subject.price.isNotBlank()) {
        "${subject.price} ₽"
    } else {
        stringResource(id = R.string.have_not_price)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Title1ForWidgetText(text = subject.name)
        
        VerticalSpacer(height = 10.dp)
        
        Title2ForWidgetText(text = price)
    }
}