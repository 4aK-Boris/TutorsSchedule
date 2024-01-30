package dmitriy.losev.students.presentation.ui.views

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dmitriy.losev.ui.theme.LocalTutorsScheduleColors

@Composable
fun AnimatedBodyHorizontal(modifier: Modifier, state: Boolean, content: @Composable (Boolean) -> Unit) {

    val backgroundColor = LocalTutorsScheduleColors.current.backgroundSecondary

    AnimatedContent(
        targetState = state,
        transitionSpec = {
            if (state) {
                (slideInHorizontally { width -> -width } + fadeIn()).togetherWith(slideOutHorizontally { width -> width } + fadeOut())
            } else {
                (slideInHorizontally { width -> width } + fadeIn()).togetherWith(slideOutHorizontally { width -> -width } + fadeOut())
            }.using(
                SizeTransform(clip = false)
            )
        },
        modifier = modifier
            .fillMaxSize()
            .background(color = backgroundColor),
        label = "lazy_column"
    ) {
        content(it)
    }
}

@Composable
fun AnimatedBody(modifier: Modifier, state: Boolean, content: @Composable (Boolean) -> Unit) {

    val backgroundColor = LocalTutorsScheduleColors.current.backgroundSecondary

    AnimatedContent(
        targetState = state,
        transitionSpec = {
            if (state) {
                (fadeIn(animationSpec = spring())).togetherWith(fadeOut(animationSpec = spring()))
            } else {
                (fadeIn(animationSpec = spring())).togetherWith(fadeOut(animationSpec = spring()))
            }.using(
                SizeTransform(clip = false)
            )
        },
        modifier = modifier
            .fillMaxSize()
            .background(color = backgroundColor),
        label = "lazy_column"
    ) {
        content(it)
    }
}