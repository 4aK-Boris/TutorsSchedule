package dmitriy.losev.ui.views.phone

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneVisualTransformation(private val phoneOffsetTranslator: PhoneOffsetTranslator = PhoneOffsetTranslator()): VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 11) text.text.substring(0..9) else text.text

        val annotatedString = AnnotatedString.Builder().run {
            for (i in trimmed.indices) {
                when (i) {
                    0 -> {
                        append("(")
                        append(trimmed[i])
                    }
                    2 -> {
                        append(trimmed[i])
                        append(") ")
                    }
                    5, 7 -> {
                        append(trimmed[i])
                        append(" - ")
                    }
                    else -> append(trimmed[i])
                }
            }
            toAnnotatedString()
        }

        return TransformedText(annotatedString, phoneOffsetTranslator)
    }
}