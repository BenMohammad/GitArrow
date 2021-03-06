package com.benmohammad.gitarrow.util

import com.benmohammad.gitarrow.data.RepoModel
import com.benmohammad.gitarrow.repo.RepoViewModel
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Period
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.PeriodFormatterBuilder
import java.text.NumberFormat

fun mapRepoModelToRepoViewModel(repoList: List<RepoModel>): List<RepoViewModel> = repoList.map {
    RepoViewModel(
        it.id.toString(),
        it.name,
        "Is fork ${if (it.fork) "YES" else "NO"}",
        "Satrgazers: ${getStringFromNumber(it.stargazers_count)}",
        "Created: ${getTimeSinceString(it.created_at)} ago",
        it.html_url
    )
}

fun getStringFromNumber(number: Int) = NumberFormat.getIntegerInstance().format(number)!!

fun getTimeSinceString(dateTimeString: String, pattern: String = "yyyy-MM-dd'T'HH:mm:ssZ"): String {
    val formatter = DateTimeFormat.forPattern(pattern)
    val createdUtc = formatter.parseDateTime(dateTimeString)
    val createdLocal = createdUtc.withZone(DateTimeZone.getDefault())
    val nowLocal = DateTime()
    val period = Period(createdLocal, nowLocal)

    val yearsMonths = PeriodFormatterBuilder()
        .appendYears()
        .appendSuffix(" year", " years")
        .appendSeparator(", ")
        .appendMonths()
        .appendSuffix(" month", " months")
        .toFormatter()

    val monthDays = PeriodFormatterBuilder()
        .appendMonths()
        .appendSuffix(" month", " months")
        .appendSeparator(", ")
        .appendDays()
        .appendSuffix( " day", " days")
        .toFormatter()

    val daysHours = PeriodFormatterBuilder()
        .appendDays()
        .appendSuffix(" day", " days")
        .appendSeparator(", ")
        .appendHours()
        .appendSuffix(" hour", " hours")
        .toFormatter()

    val hoursMinutes = PeriodFormatterBuilder()
        .appendHours()
        .appendSuffix(" hour", " hours")
        .appendSeparator(", ")
        .appendMinutes()
        .appendSuffix(" minute", " minutes")
        .toFormatter()

    val minutesSeconds = PeriodFormatterBuilder()
        .appendMinutes()
        .appendSuffix(" minute", " minutes")
        .appendSeparator(", ")
        .appendSeconds()
        .appendSuffix(" second", " seconds")
        .toFormatter()

    val seconds = PeriodFormatterBuilder()
        .appendSeconds()
        .appendSuffix(" second", " seconds")
        .toFormatter()

    if(period.years > 0)
        return yearsMonths.print(period)

    if(period.months > 0)
        return monthDays.print(period)

    if(period.days > 0)
        return daysHours.print(period)

    if(period.hours > 0)
        return hoursMinutes.print(period)

    if(period.minutes > 0)
        return minutesSeconds.print(period)

    return seconds.print(period)
}