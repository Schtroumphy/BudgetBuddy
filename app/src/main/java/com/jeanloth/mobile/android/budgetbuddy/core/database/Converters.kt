package com.jeanloth.mobile.android.budgetbuddy.core.database

import androidx.room.TypeConverter
import java.time.LocalDate

class DateTypeConverter {
    @TypeConverter
    fun fromTimestamp(value: String?): LocalDate? {
        return value?.let {  LocalDate.parse(value) }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): String {
        return date.toString()
    }
}