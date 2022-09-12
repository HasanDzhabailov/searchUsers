package com.example.kodetesttask.utils

import java.text.SimpleDateFormat
import java.util.*

fun convertDateToLong(date: String): Long {
	val formatter = SimpleDateFormat("yyyy-mm-dd")
	return formatter.parse(date).time
}

fun convertLongToTime(time: Long): String {
	val date = Date(time)
	val format = SimpleDateFormat("dd.mm.yyyy")
	return format.format(date)
}
