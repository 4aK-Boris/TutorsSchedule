package dmitriy.losev.ui.theme.shapes

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

val tutorsScheduleShapes = TutorsScheduleShapes(
    button = RoundedCornerShape(size = 18.dp),
    textField = RoundedCornerShape(size = 16.dp),
    textFieldBorder = RoundedCornerShape(size = 18.dp),
    smallShape = RoundedCornerShape(size = 4.dp),
    popUpShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp, bottomEnd = 0.dp, bottomStart = 0.dp),
    avatar = CircleShape
)