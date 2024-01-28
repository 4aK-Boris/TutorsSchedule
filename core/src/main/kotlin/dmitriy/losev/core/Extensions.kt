package dmitriy.losev.core

fun String.trimAndFirstCharToUpperCase(): String {
    return this.trim().replaceFirstChar { char ->
        if (char.isLowerCase()) {
            char.titlecase()
        } else {
            char.toString()
        }
    }
}

fun Int?.toNotNullString(): String = this?.toString() ?: EMPTY_STRING

fun String.toNullable(): String? = this.ifBlank { null }

fun String?.toNotNull(): String = this ?: EMPTY_STRING

fun String.firstChar(): String {
    val firstChar = this.firstOrNull()
    return if (firstChar == null) {
        EMPTY_STRING
    } else {
        "$firstChar."
    }
}


