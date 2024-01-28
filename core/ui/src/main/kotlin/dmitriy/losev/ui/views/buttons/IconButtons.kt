package dmitriy.losev.ui.views.buttons

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dmitriy.losev.ui.R
import dmitriy.losev.ui.theme.LocalTutorsScheduleColors
import dmitriy.losev.ui.theme.TutorsScheduleTheme

@Composable
fun DefaultIconButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    contentDescription: String,
    size: Dp,
    shape: Shape,
    enabled: Boolean,
    backgroundColor: Color,
    onClick: () -> Unit
) {

    FilledIconButton(
        modifier = modifier.size(size = size),
        onClick = onClick,
        shape = shape,
        enabled = enabled,
        colors = IconButtonDefaults.filledIconButtonColors(containerColor = backgroundColor)
    ) {
        Icon(painter = icon, contentDescription = contentDescription, tint = Color.Unspecified)
    }
}

@Composable
fun PrimaryIconButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    contentDescription: String,
    size: Dp = 24.dp,
    shape: Shape = CircleShape,
    enabled: Boolean = true,
    onClick: () -> Unit
) {

    val backgroundColor = LocalTutorsScheduleColors.current.backgroundIconPrimary

    DefaultIconButton(
        modifier = modifier,
        icon = icon,
        contentDescription = contentDescription,
        size = size,
        shape = shape,
        enabled = enabled,
        backgroundColor = backgroundColor,
        onClick = onClick
    )
}

@Composable
fun SecondaryIconButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    contentDescription: String,
    size: Dp = 24.dp,
    shape: Shape = CircleShape,
    enabled: Boolean = true,
    onClick: () -> Unit
) {

    val backgroundColor = LocalTutorsScheduleColors.current.backgroundIconSecondary

    DefaultIconButton(
        modifier = modifier,
        icon = icon,
        contentDescription = contentDescription,
        size = size,
        shape = shape,
        enabled = enabled,
        backgroundColor = backgroundColor,
        onClick = onClick
    )
}

@Composable
fun IconButtonWithoutBackground(
    modifier: Modifier = Modifier,
    icon: Painter,
    contentDescription: String,
    size: Dp = 24.dp,
    shape: Shape = CircleShape,
    enabled: Boolean = true,
    onClick: () -> Unit
) {

    DefaultIconButton(
        modifier = modifier,
        icon = icon,
        contentDescription = contentDescription,
        size = size,
        shape = shape,
        enabled = enabled,
        backgroundColor = Color.Transparent,
        onClick = onClick
    )
}

@Composable
fun ButtonBack(modifier: Modifier = Modifier, onClick: () -> Unit) {

    IconButtonWithoutBackground(
        icon = painterResource(id = R.drawable.arrow_back),
        contentDescription = stringResource(id = R.string.back),
        onClick = onClick,
        modifier = modifier
    )
}

@Composable
fun FloatingAddIconButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    PrimaryIconButton(
        modifier = modifier,
        icon = painterResource(id = R.drawable.add),
        contentDescription = stringResource(id = R.string.add),
        onClick = onClick,
        size = 42.dp,
        shape = RoundedCornerShape(size = 7.dp)
    )
}

@Composable
fun MoreIconButton(onClick: () -> Unit) {
    SecondaryIconButton(
        icon = painterResource(id = R.drawable.more),
        contentDescription = stringResource(id = R.string.more),
        onClick = onClick,
        size = 32.dp,
        shape = RoundedCornerShape(size = 6.dp)
    )
}

@Composable
fun SaveIconButton(onClick: () -> Unit) {
    PrimaryIconButton(
        icon = painterResource(id = R.drawable.done),
        contentDescription = stringResource(id = R.string.save),
        onClick = onClick,
        size = 32.dp,
        shape = RoundedCornerShape(size = 6.dp)
    )
}

@Composable
fun EditIconButton(onClick: () -> Unit) {
    PrimaryIconButton(
        icon = painterResource(id = R.drawable.edit_primary),
        contentDescription = stringResource(id = R.string.edit),
        onClick = onClick,
        size = 32.dp,
        shape = RoundedCornerShape(size = 6.dp)
    )
}

@Composable
fun CloseIconButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    PrimaryIconButton(
        modifier = modifier,
        icon = painterResource(id = R.drawable.close),
        contentDescription = stringResource(id = R.string.close),
        onClick = onClick,
        shape = RoundedCornerShape(size = 4.dp)
    )
}

@Composable
fun ContactIconButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButtonWithoutBackground(
        modifier = modifier,
        icon = painterResource(id = R.drawable.contacts),
        contentDescription = stringResource(id = R.string.contacts),
        onClick = onClick,
        shape = RoundedCornerShape(size = 4.dp)
    )
}

@Preview
@Composable
private fun ButtonBackPreview() {

    TutorsScheduleTheme {

        ButtonBack { }
    }
}

//@Composable
//fun TestIconButton(
//    modifier: Modifier = Modifier,
//    icon: Painter,
//    contentDescription: String,
//    size: Dp = 24.dp,
//    shape: Shape = CircleShape,
//    enabled: Boolean = true,
//    onClick: () -> Unit
//) {
//
//    val backgroundColor = LocalTutorsScheduleColors.current.backgroundIconPrimary
//
//    FilledIconButton(
//        modifier = modifier.size(size = size),
//        onClick = onClick,
//        shape = shape,
//        enabled = enabled,
//        colors = IconButtonDefaults.filledIconButtonColors(containerColor = backgroundColor)
//    ) {
//        Icon(painter = icon, contentDescription = contentDescription, tint = Color.Unspecified)
//    }
//}
//
//@Preview
//@Composable
//fun TestIconButtonPreview() {
//
//    TutorsScheduleTheme(darkTheme = true) {
//
//        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//
//            TestIconButton(icon = painterResource(id = R.drawable.exit), contentDescription = "", shape = RoundedCornerShape(size = 7.dp), size = 32.dp) {
//
//            }
//        }
//    }
//}

//@Composable
//fun IconButtonWithoutBackground(
//    modifier: Modifier = Modifier,
//    icon: Painter,
//    contentDescription: String,
//    size: Dp = 24.dp,
//    shape: Shape = CircleShape,
//    enabled: Boolean = true,
//    onClick: () -> Unit
//) {
//
//    Icon(
//        painter = icon,
//        contentDescription = contentDescription,
//        tint = Color.Unspecified,
//        modifier = modifier
//            .size(size = size)
//            .clip(shape = shape)
//            .clickable(onClick = onClick, enabled = enabled, role = Role.Button)
//    )
//}

@Composable
fun AuthenticationIconButton(modifier: Modifier = Modifier, icon: Painter, contentDescription: String, onClick: () -> Unit) {

    IconButton(onClick = onClick, modifier = modifier.size(size = 40.dp)) {
        Icon(painter = icon, contentDescription = contentDescription, tint = Color.Unspecified, modifier = Modifier.size(size = 40.dp))
    }
}

