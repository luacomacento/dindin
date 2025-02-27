package dev.luaoctaviano.dindin.core.data.source.local

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return if (dateLong == null) null else Date(dateLong)
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}
