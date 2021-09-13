package com.example.carousellnews.ext

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.carousellnews.ext.getDifferenceWithCurrentDateTime
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.joda.time.DateTime
import org.junit.Assert
import kotlin.time.ExperimentalTime

@RunWith(MockitoJUnitRunner::class)
class DateUtilTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    @Test
    fun assertNow() {
        val now = DateTime()
        Assert.assertEquals((getDifferenceWithCurrentDateTime(now.millis/1000L)), "Now")
    }

    @ExperimentalTime
    @Test
    fun assertHoursAgo() {
        val hoursAgo = DateTime().minusHours(3)
        Assert.assertEquals(getDifferenceWithCurrentDateTime(hoursAgo.millis/1000L), "3 hours ago")
    }

    @Test
    fun assertDaysAgo() {
        val yesterday = DateTime().minusDays(1)
        Assert.assertEquals(getDifferenceWithCurrentDateTime(yesterday.millis/1000L), "1 days ago")
    }

    @Test
    fun assertMinutesAgo() {
        val minutesAgo = DateTime().minusMinutes(4)
        Assert.assertEquals(getDifferenceWithCurrentDateTime(minutesAgo.millis/1000L), "4 minutes ago")
    }

    @Test
    fun assertWeeksAgo() {
        val weeksAgo = DateTime().minusWeeks(4)
        Assert.assertEquals(getDifferenceWithCurrentDateTime(weeksAgo.millis/1000L), "4 weeks ago")
    }

    @Test
    fun assertMonthsAgo() {
        val monthsAgo = DateTime().minusMonths(10)
        Assert.assertEquals(getDifferenceWithCurrentDateTime(monthsAgo.millis/1000L), "10 months ago")
    }

    @Test
    fun assertYearsAgo() {
        val yearsAgo = DateTime().minusYears(3)
        Assert.assertEquals(getDifferenceWithCurrentDateTime(yearsAgo.millis/1000L), "3 years ago")
    }
}