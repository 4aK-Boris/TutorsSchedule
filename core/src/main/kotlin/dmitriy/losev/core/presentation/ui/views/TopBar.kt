package dmitriy.losev.core.presentation.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import dmitriy.losev.core.theme.TutorsScheduleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    titleText: String,
    leftIcon: ImageVector,
    rightIcon: ImageVector? = null,
    leftContentDescription: String,
    rightContentDescription: String = "",
    onLeftIconClick: () -> Unit,
    onRightIconClick: () -> Unit = {}
) {


    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 60.dp),
        title = {
            Box(modifier = Modifier.height(height = 60.dp), contentAlignment = Alignment.CenterStart) {
                Text(
                    text = titleText,
                    style = TutorsScheduleTheme.typography.mainTitle
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = onLeftIconClick, modifier = Modifier.size(size = 60.dp)) {
                Icon(
                    imageVector = leftIcon,
                    contentDescription = leftContentDescription,
                    modifier = Modifier.size(size = 24.dp)
                )
            }
        },
        actions = {
            rightIcon?.let { icon ->
                IconButton(onClick = onRightIconClick, modifier = Modifier.size(size = 60.dp)) {
                    Icon(
                        imageVector = icon,
                        contentDescription = rightContentDescription,
                        modifier = Modifier.size(size = 24.dp)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = TutorsScheduleTheme.colors.topBar
        )
    )
}