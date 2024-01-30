package dmitriy.losev.students.core

data class StudentsHandler(var studentIds: List<String> = emptyList()) {

    fun clear() {
        studentIds = emptyList()
    }
}