package com.jeanloth.mobile.android.budgetbuddy.core.database

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector
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

class ImageVectorConverter {
    private fun iconByName(name: String): ImageVector {
        val cl = Class.forName("androidx.compose.material.icons.filled.${name}Kt")
        val method = cl.declaredMethods.first()
        return method.invoke(null, Icons.Filled) as ImageVector
    }

    private fun getIconName(icon: ImageVector?): String {
        return icon?.name?.split(".")?.get(1) ?: ""
    }

    @TypeConverter
    fun fromValue(value: String?): ImageVector? {
        return value?.let {  iconByName(value) }
    }

    @TypeConverter
    fun toValue(icon: ImageVector?): String {
        return getIconName(icon)
    }
}