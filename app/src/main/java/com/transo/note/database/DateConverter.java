package com.transo.note.database;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static Long toTimeStamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Date toTimeStamp(Long timeStamp) {
        return timeStamp == null ? null : new Date(timeStamp);
    }

}
