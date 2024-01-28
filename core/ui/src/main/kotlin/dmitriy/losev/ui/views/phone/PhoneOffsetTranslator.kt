package dmitriy.losev.ui.views.phone

import androidx.compose.ui.text.input.OffsetMapping

class PhoneOffsetTranslator: OffsetMapping {

    override fun originalToTransformed(offset: Int): Int {
        if (offset <= 0) return offset
        if (offset <= 2) return offset + 1
        if (offset <= 5) return offset + 3
        if (offset <= 7) return offset + 6
        if (offset <= 10) return offset + 9
        return 19
    }

    override fun transformedToOriginal(offset: Int): Int {
        if (offset <= 1) return offset
        if (offset <= 3) return offset - 1
        if (offset <= 7) return offset - 3
        if (offset <= 13) return offset - 6
        if (offset <= 18) return offset - 9
        return 10
    }
}