package com.example.tvshows.core.helper

import android.annotation.SuppressLint
import com.example.tvshows.core.Constants
import java.text.ParseException
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
object DateFormatHelper {

    fun formatDate(date: String): String {
        var spf = SimpleDateFormat(Constants.UNFORMATTED_DATE)
        try {
            val newDate = spf.parse(date)
            spf = SimpleDateFormat(Constants.FORMATTED_DATE)
            return spf.format(newDate)
        } catch (e: ParseException) {
            return date
        }

    }
}
