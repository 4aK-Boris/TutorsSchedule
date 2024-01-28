package dmitriy.losev.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dmitriy.losev.ui.R
import dmitriy.losev.ui.views.buttons.ButtonBack

@Composable
fun LogoWithBackButton(back: () -> Unit) {

    Box(modifier = Modifier.fillMaxWidth()) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(id = R.string.logo),
            modifier = Modifier.align(alignment = Alignment.TopCenter).offset(y = (-24).dp)
        )

        ButtonBack(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .padding(start = 16.dp, top = 16.dp),
            onClick = back
        )
    }
}