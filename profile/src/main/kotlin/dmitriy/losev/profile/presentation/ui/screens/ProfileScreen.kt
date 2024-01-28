package dmitriy.losev.profile.presentation.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dmitriy.losev.profile.R
import dmitriy.losev.profile.core.ProfileNavigationListener
import dmitriy.losev.profile.presentation.ui.views.BlockWithIcon
import dmitriy.losev.profile.presentation.ui.views.SettingsIconButton
import dmitriy.losev.profile.presentation.ui.views.Subjects
import dmitriy.losev.profile.presentation.viewmodels.ProfileScreenViewModel
import dmitriy.losev.ui.theme.LocalTutorsScheduleColors
import dmitriy.losev.ui.theme.LocalTutorsScheduleShapes
import dmitriy.losev.ui.views.HorizontalSpacer
import dmitriy.losev.ui.views.Title2ForWidgetText
import dmitriy.losev.ui.views.Title2Text
import dmitriy.losev.ui.views.VerticalSpacer
import dmitriy.losev.ui.views.boxes.ColumnSecondaryWithLoader
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    profileNavigationListener: ProfileNavigationListener,
    viewModel: ProfileScreenViewModel = koinViewModel()
) {
    val colors = LocalTutorsScheduleColors.current

    val avatarBorderColor = colors.iconPrimary

    val avatarShape = LocalTutorsScheduleShapes.current.avatar

    val avatar by viewModel.avatar.collectAsState(initial = null)
    val displayName by viewModel.displayName.collectAsState()
    val phoneNumber by viewModel.phoneNumber.collectAsState()
    val email by viewModel.email.collectAsState()

    val subjects by viewModel.subjects.collectAsState()

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    LaunchedEffect(key1 = Unit) {
        viewModel.checkAuthAndLoadData(profileNavigationListener)
    }

    ColumnSecondaryWithLoader(viewModel = viewModel) {

        VerticalSpacer(height = 24.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(avatar)
                        .crossfade(enable = true)
                        .build(),
                    placeholder = painterResource(R.drawable.avatar),
                    error = painterResource(R.drawable.avatar),
                    fallback = painterResource(R.drawable.avatar),
                    contentDescription = stringResource(R.string.avatar),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(size = 48.dp)
                        .clip(avatarShape)
                        .border(width = 2.dp, color = avatarBorderColor, shape = avatarShape)
                )

                HorizontalSpacer(width = 8.dp)

                Title2Text(text = displayName, modifier = Modifier.width(width = screenWidth - 128.dp))
            }

            SettingsIconButton {
                viewModel.navigateToEditProfileScreen(profileNavigationListener)
            }
        }

        VerticalSpacer(height = 16.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Title2Text(text = stringResource(id = R.string.phone))

            HorizontalSpacer(width = 12.dp)

            Title2ForWidgetText(text = phoneNumber)
        }

        VerticalSpacer(height = 20.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Title2Text(text = stringResource(id = R.string.email), modifier = Modifier.width(width = screenWidth - 244.dp))

            HorizontalSpacer(width = 12.dp)

            Title2ForWidgetText(text = email)
        }

        VerticalSpacer(height = 28.dp)

        BlockWithIcon(title = stringResource(id = R.string.schedule_title), hint = stringResource(id = R.string.schedule_title_hint)) {

        }

        VerticalSpacer(height = 16.dp)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(height = 120.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Здесь что-то будет")
        }

        VerticalSpacer(height = 32.dp)

        BlockWithIcon(title = stringResource(id = R.string.finance_title), hint = stringResource(id = R.string.finance_title_hint)) {

        }

        VerticalSpacer(height = 16.dp)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(height = 160.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Здесь что-то будет")
        }

        VerticalSpacer(height = 32.dp)

        BlockWithIcon(title = stringResource(id = R.string.subjects_title), hint = stringResource(id = R.string.schedule_title_hint)) {
            viewModel.navigateToSubjectsScreen(profileNavigationListener)
        }

        VerticalSpacer(height = 16.dp)

        Subjects(subjects = subjects)

        VerticalSpacer(height = 32.dp)
    }
}
