package dmitriy.losev.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dmitriy.losev.ui.R
import dmitriy.losev.ui.theme.colors.White
import dmitriy.losev.ui.theme.typography.toastTextStyle

@Composable
fun ErrorToast(text: String) {

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(color = White, shape = RoundedCornerShape(size = 16.dp))
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            HorizontalSpacer(width = 16.dp)

            Text(text = text, style = toastTextStyle, modifier = Modifier.width(width = screenWidth - 92.dp))

            HorizontalSpacer(width = 4.dp)

            Icon(painter = painterResource(id = R.drawable.error_primary), contentDescription = stringResource(id = R.string.error), tint = Color.Unspecified)

            HorizontalSpacer(width = 16.dp)
        }
    }
}