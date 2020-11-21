package com.bonadenys.registrationtc.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

class DateFormat {
    companion object {
        @SuppressLint("SimpleDateFormat")
        fun convert(date: String): String {
            val from = SimpleDateFormat("MMM dd, yyyy").parse(date)
            return SimpleDateFormat("dd-MM-yyyy").format(from)
        }
    }
}