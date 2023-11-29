package dmitriy.losev.profile.presentation.ui.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dmitriy.losev.core.presentation.ui.views.TopBar
import dmitriy.losev.profile.R

@Composable
fun ProfileTopBar(
    openDrawer: () -> Unit,
    isAuthenticated: Boolean,
    navigateToEditProfileScreen: () -> Unit,
) {

    val rightIcon = if (isAuthenticated) {
        Icons.Default.Edit
    } else {
        null
    }

    TopBar(
        titleText = stringResource(id = R.string.profile),
        leftIcon = Icons.Default.Menu,
        leftContentDescription = stringResource(id = R.string.menu),
        onLeftIconClick = openDrawer,
        rightIcon = rightIcon,
        rightContentDescription = stringResource(id = R.string.profile_edit),
        onRightIconClick = navigateToEditProfileScreen
    )
}