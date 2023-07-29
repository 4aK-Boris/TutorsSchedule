package dmitriy.losev.auth.presentation.ui.views

import androidx.compose.ui.text.input.OffsetMapping

class PhoneOffsetMapper : OffsetMapping {

    override fun originalToTransformed(offset: Int): Int {
        if (offset <= 2) return offset
        if (offset <= 4) return offset + 2
        if (offset <= 7) return offset + 4
        if (offset <= 9) return offset + 7
        if (offset <= 12) return offset + 10
        return 22
    }

    override fun transformedToOriginal(offset: Int): Int {
        if (offset <= 5) return offset - 2
        if (offset <= 9) return offset - 4
        if (offset <= 15) return offset - 7
        if (offset <= 20) return offset - 10
        return 12
    }
}