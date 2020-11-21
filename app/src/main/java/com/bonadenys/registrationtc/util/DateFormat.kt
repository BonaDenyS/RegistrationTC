package com.bonadenys.registrationtc.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DateFormat {
    companion object {
        @SuppressLint("SimpleDateFormat")
        fun convert(date: String): String {
            val from = SimpleDateFormat("MMM dd, yyyy").parse(date)
            return SimpleDateFormat("dd-MM-yyyy").format(from)
        }
    }
}