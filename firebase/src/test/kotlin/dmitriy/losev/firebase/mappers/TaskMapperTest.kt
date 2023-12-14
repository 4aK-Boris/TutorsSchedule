package dmitriy.losev.firebase.mappers

import dmitriy.losev.firebase.data.dto.TaskDTO
import dmitriy.losev.firebase.data.mappers.TaskMapper
import dmitriy.losev.firebase.domain.models.types.LessonStatus
import dmitriy.losev.firebase.domain.models.types.PaidStatus
import dmitriy.losev.firebase.domain.models.Task
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.ZonedDateTime
import kotlin.test.assertEquals

class TaskMapperTest {

    private val taskMapper = TaskMapper()

    @ParameterizedTest
    @MethodSource("straight")
    fun testMap(
        task: Task,
        expectedTaskDTO: TaskDTO
    ) {
        val actualResult = taskMapper.map(value = task)

        assertEquals(expectedTaskDTO, actualResult)
    }

    @ParameterizedTest
    @MethodSource("reverse")
    fun testInverseMap(
        taskDTO: TaskDTO,
        expectedTask: Task
    ) {
        val actualResult = taskMapper.map(value = taskDTO)

        assertEquals(expectedTask.id, actualResult.id)
        assertEquals(expectedTask.lessonId, actualResult.lessonId)
        assertEquals(expectedTask.status, actualResult.status)
        assertEquals(expectedTask.paidStatus, actualResult.paidStatus)

        assertEquals(expectedTask.dateTime.year, actualResult.dateTime.year)
        assertEquals(expectedTask.dateTime.month, actualResult.dateTime.month)
        assertEquals(expectedTask.dateTime.dayOfMonth, actualResult.dateTime.dayOfMonth)
        assertEquals(expectedTask.dateTime.hour, actualResult.dateTime.hour)
        assertEquals(expectedTask.dateTime.minute, actualResult.dateTime.minute)
        assertEquals(expectedTask.dateTime.second, actualResult.dateTime.second)
    }

    companion object {

        private const val TASK_ID = "task_id"
        private const val LESSON_ID = "lesson_id"
        private const val PERIOD_ID = "period_id"

        private val dateTime = ZonedDateTime.now()
        private val dateTimeOfLong = dateTime.toEpochSecond()
        private val zoneId = dateTime.zone.id

        @JvmStatic
        fun straight() = listOf(
            Arguments.of(
                Task(
                    id = null,
                    lessonId = "",
                    periodId = "",
                    dateTime = dateTime,
                    status = LessonStatus.PLANNED,
                    paidStatus = PaidStatus.NO_PAID
                ),
                TaskDTO(
                    id = null,
                    lessonId = null,
                    periodId = null,
                    dateTime = dateTimeOfLong,
                    zoneId = zoneId,
                    status = LessonStatus.PLANNED.name,
                    paidStatus = PaidStatus.NO_PAID.name
                )
            ),
            Arguments.of(
                Task(
                    id = TASK_ID,
                    lessonId = LESSON_ID,
                    periodId = PERIOD_ID,
                    dateTime = dateTime,
                    status = LessonStatus.MOVED,
                    paidStatus = PaidStatus.PAID
                ),
                TaskDTO(
                    id = TASK_ID,
                    lessonId = LESSON_ID,
                    periodId = PERIOD_ID,
                    dateTime = dateTimeOfLong,
                    zoneId = zoneId,
                    status = LessonStatus.MOVED.name,
                    paidStatus = PaidStatus.PAID.name
                )
            )
        )

        @JvmStatic
        fun reverse() = listOf(
            Arguments.of(
                TaskDTO(
                    id = null,
                    lessonId = null,
                    periodId = null,
                    dateTime = dateTimeOfLong,
                    zoneId = zoneId,
                    status = LessonStatus.PLANNED.name,
                    paidStatus = PaidStatus.NO_PAID.name
                ),
                Task(
                    id = null,
                    lessonId = "",
                    periodId = "",
                    dateTime = dateTime,
                    status = LessonStatus.PLANNED,
                    paidStatus = PaidStatus.NO_PAID
                )
            ),
            Arguments.of(
                TaskDTO(
                    id = TASK_ID,
                    lessonId = LESSON_ID,
                    periodId = PERIOD_ID,
                    dateTime = dateTimeOfLong,
                    zoneId = zoneId,
                    status = LessonStatus.MOVED.name,
                    paidStatus = PaidStatus.PAID.name
                ),
                Task(
                    id = TASK_ID,
                    lessonId = LESSON_ID,
                    periodId = PERIOD_ID,
                    dateTime = dateTime,
                    status = LessonStatus.MOVED,
                    paidStatus = PaidStatus.PAID
                )
            )
        )
    }
}