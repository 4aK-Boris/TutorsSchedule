package dmitriy.losev.firebase.mappers

import dmitriy.losev.firebase.data.dto.PeriodDTO
import dmitriy.losev.firebase.data.mappers.PeriodMapper
import dmitriy.losev.core.models.types.DayOfWeek
import dmitriy.losev.firebase.domain.models.Period
import dmitriy.losev.core.models.types.PeriodType
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.ZonedDateTime
import kotlin.test.assertEquals

class PeriodMapperTest {

    private val periodMapper = PeriodMapper()

    @ParameterizedTest
    @MethodSource("straight")
    fun testMap(
        period: Period,
        expectedPeriodDTO: PeriodDTO
    ) {
        val actualResult = periodMapper.map(value = period)

        assertEquals(expectedPeriodDTO, actualResult)
    }

    @ParameterizedTest
    @MethodSource("reverse")
    fun testInverseMap(
        periodDTO: PeriodDTO,
        expectedPeriod: Period
    ) {
        val actualResult = periodMapper.map(value = periodDTO)

        assertEquals(expectedPeriod.id, actualResult.id)
        assertEquals(expectedPeriod.lessonId, actualResult.lessonId)
        assertEquals(expectedPeriod.type, actualResult.type)
        assertEquals(expectedPeriod.dayOfWeek, actualResult.dayOfWeek)

        assertEquals(expectedPeriod.firstDateTime.year, actualResult.firstDateTime.year)
        assertEquals(expectedPeriod.firstDateTime.month, actualResult.firstDateTime.month)
        assertEquals(expectedPeriod.firstDateTime.dayOfMonth, actualResult.firstDateTime.dayOfMonth)
        assertEquals(expectedPeriod.firstDateTime.hour, actualResult.firstDateTime.hour)
        assertEquals(expectedPeriod.firstDateTime.minute, actualResult.firstDateTime.minute)
        assertEquals(expectedPeriod.firstDateTime.second, actualResult.firstDateTime.second)
    }

    companion object {

        private const val PERIOD_ID = "period_id"
        private const val LESSON_ID = "lesson_id"

        private val dateTime = ZonedDateTime.now()
        private val dateTimeOfLong = dateTime.toEpochSecond()
        private val zoneId = dateTime.zone.id

        @JvmStatic
        fun straight() = listOf(
            Arguments.of(
                Period(
                    id = null,
                    lessonId = "",
                    type = PeriodType.WEEK,
                    dayOfWeek = DayOfWeek.MONDAY,
                    firstDateTime = dateTime
                ),
                PeriodDTO(
                    id = null,
                    lessonId = null,
                    type = PeriodType.WEEK.name,
                    dayOfWeek = DayOfWeek.MONDAY.name,
                    firstDateTime = dateTimeOfLong,
                    zoneId = zoneId
                )
            ),
            Arguments.of(
                Period(
                    id = PERIOD_ID,
                    lessonId = LESSON_ID,
                    type = PeriodType.WEEK,
                    dayOfWeek = DayOfWeek.MONDAY,
                    firstDateTime = dateTime
                ),
                PeriodDTO(
                    id = PERIOD_ID,
                    lessonId = LESSON_ID,
                    type = PeriodType.WEEK.name,
                    dayOfWeek = DayOfWeek.MONDAY.name,
                    firstDateTime = dateTimeOfLong,
                    zoneId = zoneId
                )
            )
        )

        @JvmStatic
        fun reverse() = listOf(
            Arguments.of(
                PeriodDTO(
                    id = null,
                    lessonId = null,
                    type = PeriodType.WEEK.name,
                    dayOfWeek = DayOfWeek.MONDAY.name,
                    firstDateTime = dateTimeOfLong,
                    zoneId = zoneId
                ),
                Period(
                    id = null,
                    lessonId = "",
                    type = PeriodType.WEEK,
                    dayOfWeek = DayOfWeek.MONDAY,
                    firstDateTime = dateTime
                )
            ),
            Arguments.of(
                PeriodDTO(
                    id = PERIOD_ID,
                    lessonId = LESSON_ID,
                    type = PeriodType.WEEK.name,
                    dayOfWeek = DayOfWeek.MONDAY.name,
                    firstDateTime = dateTimeOfLong,
                    zoneId = zoneId
                ),
                Period(
                    id = PERIOD_ID,
                    lessonId = LESSON_ID,
                    type = PeriodType.WEEK,
                    dayOfWeek = DayOfWeek.MONDAY,
                    firstDateTime = dateTime
                )
            )
        )
    }
}