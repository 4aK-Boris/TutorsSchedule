package dmitriy.losev.profile.presentation.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dmitriy.losev.core.theme.TutorsScheduleTheme
import dmitriy.losev.profile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordTopBar(onClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.chenge_password_title),
                style = TutorsScheduleTheme.typography.mainTitle
            )
        },
        navigationIcon = {
            Box(
                modifier = Modifier.padding(horizontal = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    modifier = Modifier.size(size = 36.dp),
                    onClick = onClick
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = stringResource(id = R.string.go_back),
                        modifier = Modifier.size(size = 36.dp)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = TutorsScheduleTheme.colors.topBar
        )
    )
}