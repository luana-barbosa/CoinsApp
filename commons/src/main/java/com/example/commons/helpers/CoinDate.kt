package com.example.commons.helpers

import java.text.SimpleDateFormat
import java.util.*

class CoinDate {

    fun callDate(): String {
        val date = Calendar.getInstance().time
        var dateTimeFormat = SimpleDateFormat("d MMM yyyy")
        return dateTimeFormat.format(date)
    }
}