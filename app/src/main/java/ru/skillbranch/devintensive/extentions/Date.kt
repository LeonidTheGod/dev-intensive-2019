package ru.skillbranch.devintensive.extentions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = SECOND * 60
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

val firstVariant = mapOf(TimeUnits.MINUTE to "минуту", TimeUnits.HOUR to "час", TimeUnits.DAY to "день", TimeUnits.SECOND to "секунду")
val secondVariant = mapOf(TimeUnits.MINUTE to "минуты", TimeUnits.HOUR to "часа", TimeUnits.DAY to "дня", TimeUnits.SECOND to "секунды")
val thirdVariant = mapOf(TimeUnits.MINUTE to "минут", TimeUnits.HOUR to "часов", TimeUnits.DAY to "дней", TimeUnits.SECOND to "секунд")

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date{
    var time = this.time
    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date:Date = Date()): String {

    val inputDateString = this.format("ss, mm, HH, dd, MM, yy").split(", ")
    val nowDateString = date.format("ss, mm, HH, dd, MM, yy").split(", ")
    val diff = mutableListOf<Int>()

    for (i in 0 until 6) {
        diff.add(nowDateString[i].toInt() - inputDateString[i].toInt())
    }

    return when(val timeSec = (diff[0] + diff[1]*60 +
            diff[2]*60*60 + diff[3]*60*60*24 +
            diff[4]*30*60*60*24 + diff[5]*365*60*60*24)) {
        in 0..1 -> "только что"
        in 2..45 -> "несколько секунд назад"
        in 46..75 -> "минуту назад"
        in 76..45 * 60 -> TimeUnits.MINUTE.plural(timeSec/60) + " назад"
        in 45 * 60 + 1..75 * 60 -> "час назад"
        in 75 * 60 + 1..22 * 3600 -> TimeUnits.HOUR.plural(timeSec/60/60) + " назад"
        in 22 * 3600 + 1..26 * 3600 -> "день назад"
        in 26 * 3600 + 1..360 * 86400 -> TimeUnits.DAY.plural(timeSec/60/60/24) + " назад"
        in 360 * 86400 + 1..214700000000000 -> "более года назад"
        else -> ""
    }
}

fun getTimeString(value: Int, units: TimeUnits): String {

    return if (((value%100).toString() in "11,12,13,14" && (value%100).toString() !in "1,2,3,4") ||
        (value%10).toString() in "0,5,6,7,8,9")
        "$value ${thirdVariant[units]}"
    else if ((value%10).toString() in "2,3,4")
        "$value ${secondVariant[units]}"
    else "$value ${firstVariant[units]}"
}

interface Plural{
    fun plural(value: Int): String
}

enum class TimeUnits: Plural{
    SECOND { override fun plural(value: Int) = getTimeString(value, SECOND) },
    MINUTE { override fun plural(value: Int) = getTimeString(value,  MINUTE) },
    HOUR { override fun plural(value: Int) = getTimeString(value, HOUR) },
    DAY { override fun plural(value: Int) = getTimeString(value, DAY) }
}