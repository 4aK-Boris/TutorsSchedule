package dmitriy.losev.auth.presentation.ui.views

import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp

class PhoneVisualTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {

        val trimmed = if (text.text.length >= 12) text.text.substring(0..11) else text.text

        val annotatedString = AnnotatedString.Builder().run {
            for (i in trimmed.indices) {
                when (i) {
                    2 -> {
                        append(" (")
                        append(trimmed[i])
                    }

                    4 -> {
                        append(trimmed[i])
                        append(") ")
                    }

                    7, 9 -> {
                        append(trimmed[i])
                        append(" - ")
                    }

                    else -> append(trimmed[i])
                }
            }
            pushStyle(
                SpanStyle(
                    color = LightGray,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                )
            )
            append(MASK.takeLast(MASK.length - length))
            toAnnotatedString()
        }
        return TransformedText(annotatedString, PhoneOffsetMapper())
    }
    companion object {
        const val MASK = "+7 (___) ___   __   __"
    }
}
