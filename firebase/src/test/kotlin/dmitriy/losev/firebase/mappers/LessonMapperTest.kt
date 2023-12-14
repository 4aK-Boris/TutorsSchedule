package dmitriy.losev.firebase.mappers

import dmitriy.losev.firebase.data.dto.LessonDTO
import dmitriy.losev.firebase.data.mappers.LessonMapper
import dmitriy.losev.firebase.domain.models.Lesson
import dmitriy.losev.firebase.domain.models.types.LessonType
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class LessonMapperTest {

    private val lessonMapper = LessonMapper()

    @ParameterizedTest
    @MethodSource("straight")
    fun testMap(
        lesson: Lesson,
        expectedLessonDTO: LessonDTO
    ) {
        val actualResult = lessonMapper.map(value = lesson)

        assertEquals(expectedLessonDTO, actualResult)
    }

    @ParameterizedTest
    @MethodSource("reverse")
    fun testInverseMap(
        lessonDTO: LessonDTO,
        expectedLesson: Lesson
    ) {
        val actualResult = lessonMapper.map(value = lessonDTO)

        assertEquals(expectedLesson, actualResult)
    }

    companion object {

        private const val ID = "99432i,c4234-932409932=-4c32"
        private const val STUDENT_OR_GROUP_ID = "student_or_group_id"
        private const val SUBJECT_ID = "subject_id"
        private const val PRICE = 1000
        private const val DURATION = 90

        @JvmStatic
        fun straight() = listOf(
            Arguments.of(
                Lesson(
                    id = null,
                    type = LessonType.GROUP,
                    studentOrGroupId = "",
                    subjectId = "",
                    price = PRICE,
                    duration = DURATION
                ),
                LessonDTO(
                    id = null,
                    type = LessonType.GROUP.name,
                    studentOrGroupId = null,
                    subjectId = null,
                    price = PRICE,
                    duration = DURATION
                )
            ),
            Arguments.of(
                Lesson(
                    id = ID,
                    type = LessonType.STUDENT,
                    studentOrGroupId = STUDENT_OR_GROUP_ID,
                    subjectId = SUBJECT_ID,
                    price = PRICE,
                    duration = DURATION
                ),
                LessonDTO(
                    id = ID,
                    type = LessonType.STUDENT.name,
                    studentOrGroupId = STUDENT_OR_GROUP_ID,
                    subjectId = SUBJECT_ID,
                    price = PRICE,
                    duration = DURATION
                )
            )
        )

        @JvmStatic
        fun reverse() = listOf(
            Arguments.of(
                LessonDTO(
                    id = null,
                    type = LessonType.GROUP.name,
                    studentOrGroupId = null,
                    subjectId = null,
                    price = PRICE,
                    duration = DURATION
                ),
                Lesson(
                    id = null,
                    type = LessonType.GROUP,
                    studentOrGroupId = "",
                    subjectId = "",
                    price = PRICE,
                    duration = DURATION
                )
            ),
            Arguments.of(
                LessonDTO(
                    id = ID,
                    type = LessonType.STUDENT.name,
                    studentOrGroupId = STUDENT_OR_GROUP_ID,
                    subjectId = SUBJECT_ID,
                    price = PRICE,
                    duration = DURATION
                ),
                Lesson(
                    id = ID,
                    type = LessonType.STUDENT,
                    studentOrGroupId = STUDENT_OR_GROUP_ID,
                    subjectId = SUBJECT_ID,
                    price = PRICE,
                    duration = DURATION
                )
            )
        )
    }
}