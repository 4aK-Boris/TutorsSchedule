package dmitriy.losev.firebase.core

internal fun String.toNullable(): String? = this.ifBlank { null }

internal fun String?.toNotNull(): String = this ?: EMPTY_STRING