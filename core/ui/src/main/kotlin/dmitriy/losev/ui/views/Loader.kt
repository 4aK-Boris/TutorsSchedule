package dmitriy.losev.ui.views

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dmitriy.losev.ui.theme.LocalTutorsScheduleColors
import dmitriy.losev.ui.theme.TutorsScheduleTheme

@Composable
fun Loader() {

    val color = LocalTutorsScheduleColors.current.iconPrimary

    val infiniteTransition = rememberInfiniteTransition(label = "loader")

    val animatedOffsetX by infiniteTransition.animateValue(
        initialValue = 0.dp,
        targetValue = 0.dp,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 3200
                0.dp at 0 with FastOutSlowInEasing
                (-21).dp at 350 with FastOutSlowInEasing
                (-21).dp at 800 with FastOutSlowInEasing
                0.dp at 1150 with FastOutSlowInEasing
                0.dp at 1600 with FastOutSlowInEasing
                15.dp at 1950 with FastOutSlowInEasing
                15.dp at 2400 with FastOutSlowInEasing
                0.dp at 2750 with FastOutSlowInEasing
                0.dp at 3200 with FastOutSlowInEasing
            },
            repeatMode = RepeatMode.Restart
        ),
        typeConverter = Dp.VectorConverter,
        label = "loader_offset_x"
    )

    val animatedOffsetY by infiniteTransition.animateValue(
        initialValue = 0.dp,
        targetValue = 0.dp,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 3200
                0.dp at 0 with FastOutSlowInEasing
                15.dp at 350 with FastOutSlowInEasing
                15.dp at 800 with FastOutSlowInEasing
                0.dp at 1150 with FastOutSlowInEasing
                0.dp at 1600 with FastOutSlowInEasing
                21.dp at 1950 with FastOutSlowInEasing
                21.dp at 2400 with FastOutSlowInEasing
                0.dp at 2750 with FastOutSlowInEasing
                0.dp at 3200 with FastOutSlowInEasing
            },
            repeatMode = RepeatMode.Restart
        ),
        typeConverter = Dp.VectorConverter,
        label = "loader_offset_y"
    )

    val animatedRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 3200
                0f at 0 with FastOutSlowInEasing
                90f at 350 with FastOutSlowInEasing
                90f at 800 with FastOutSlowInEasing
                0f at 1150 with FastOutSlowInEasing
                0f at 1600 with FastOutSlowInEasing
                (-90).toFloat() at 1950 with FastOutSlowInEasing
                (-90).toFloat() at 2400 with FastOutSlowInEasing
                0f at 2750 with FastOutSlowInEasing
                0f at 3200 with FastOutSlowInEasing
            },
            repeatMode = RepeatMode.Restart
        ),
        label = "loader_rotation"
    )

    val animatedSize by infiniteTransition.animateValue(
        initialValue = 14.dp,
        targetValue = 14.dp,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 3200
                14.dp at 0 with FastOutSlowInEasing
                36.dp at 350 with FastOutSlowInEasing
                36.dp at 800 with FastOutSlowInEasing
                14.dp at 1150 with FastOutSlowInEasing
                14.dp at 1600 with FastOutSlowInEasing
                36.dp at 1950 with FastOutSlowInEasing
                36.dp at 2400 with FastOutSlowInEasing
                14.dp at 2750 with FastOutSlowInEasing
                14.dp at 3200 with FastOutSlowInEasing
            },
            repeatMode = RepeatMode.Restart
        ),
        typeConverter = Dp.VectorConverter,
        label = "loader_size"
    )

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Box(
            modifier = Modifier
                .offset(x = animatedOffsetX, y = -animatedOffsetY)
                .rotate(degrees = animatedRotation)
                .size(size = animatedSize)
                .background(color = color, shape = RoundedCornerShape(size = 2.dp))

        )

        Box(
            modifier = Modifier
                .offset(x = -animatedOffsetX, y = animatedOffsetY)
                .rotate(degrees = animatedRotation)
                .size(size = animatedSize)
                .background(color = color, shape = RoundedCornerShape(size = 2.dp))

        )
    }
}

@Preview
@Composable
private fun LoaderPreview() {

    TutorsScheduleTheme {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Loader()
        }
    }
}

