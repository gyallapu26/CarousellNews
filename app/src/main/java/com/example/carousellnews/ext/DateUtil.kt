package com.example.carousellnews.ext

import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Days
import org.joda.time.Hours
import org.joda.time.Minutes
import org.joda.time.Months
import org.joda.time.Weeks
import org.joda.time.Years

fun getDifferenceWithCurrentDateTime(
    inputDateInSecondsLong: Long,
): String {
    val inputDateTime = DateTime(inputDateInSecondsLong * 1000L, DateTimeZone.UTC)

    val yearsBetween = Years.yearsBetween(inputDateTime, DateTime()).years
    if (yearsBetween > 0) return "$yearsBetween years ago"

    val monthsBetween = Months.monthsBetween(inputDateTime, DateTime()).months
    if (monthsBetween > 0) return "$monthsBetween months ago"

    val weeksBetween = Weeks.weeksBetween(inputDateTime, DateTime()).weeks
    if (weeksBetween > 0) return "$weeksBetween weeks ago"

    val daysBetween = Days.daysBetween(inputDateTime, DateTime()).days
    if (daysBetween > 0) return "$daysBetween days ago"

    val hoursBetween = Hours.hoursBetween(inputDateTime, DateTime()).hours
    if (hoursBetween > 0) return "$hoursBetween hours ago"

    val minutesBetween = Minutes.minutesBetween(inputDateTime, DateTime()).minutes
    if (minutesBetween > 0) return "$minutesBetween minutes ago"

    return "Now"

}


fun View.visible() {
    if (!this.isVisible) this.visibility = View.VISIBLE
}

fun View.gone() {
    if (!this.isGone) this.visibility = View.GONE
}

