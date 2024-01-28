package dmitriy.losev.ui.views.boxes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.theme.LocalTutorsScheduleColors
import dmitriy.losev.ui.views.Loader

@Composable
fun ColumnSecondaryWithLoader(
    modifier: Modifier = Modifier,
    viewModel: BaseViewModel,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    content: @Composable () -> Unit
) {

    val scrollState = rememberScrollState()

    val isLoading by viewModel.isLoading.collectAsState()

    val backgroundColor = LocalTutorsScheduleColors.current.backgroundSecondary

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = backgroundColor)
            .verticalScroll(state = scrollState),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }

    if (isLoading) {
        Loader()
    }
}

@Composable
fun ColumnSecondary(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    content: @Composable () -> Unit
) {

    val scrollState = rememberScrollState()

    val backgroundColor = LocalTutorsScheduleColors.current.backgroundSecondary

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = backgroundColor)
            .verticalScroll(state = scrollState),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }
}

@Composable
fun ColumnPrimaryWithLoader(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    viewModel: BaseViewModel,
    content: @Composable () -> Unit
) {

    val scrollState = rememberScrollState()

    val isLoading by viewModel.isLoading.collectAsState()

    val backgroundColor = LocalTutorsScheduleColors.current.backgroundPrimary

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = backgroundColor)
            .verticalScroll(state = scrollState),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }

    if (isLoading) {
        Loader()
    }
}

@Composable
fun ColumnPrimary(modifier: Modifier = Modifier, verticalArrangement: Arrangement.Vertical = Arrangement.Top, content: @Composable () -> Unit) {

    val scrollState = rememberScrollState()

    val backgroundColor = LocalTutorsScheduleColors.current.backgroundPrimary

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = backgroundColor)
            .verticalScroll(state = scrollState),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }
}