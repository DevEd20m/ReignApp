package com.faztbit.reignapp.ui.databinding

import android.widget.TextView
import com.faztbit.reignapp.R
import com.faztbit.reignapp.utils.Constant
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


fun TextView.changeFormatDateTime( value: String) {

    val suffix = this.context.getString(R.string.string_ago)
    val dateFormat = SimpleDateFormat(Constant.FORMAT_DATE_TIME)
    val pasTime = dateFormat.parse(value)
    val nowTime = Date()
    val dateDiff = nowTime.time - pasTime.time
    val second: Long = TimeUnit.MILLISECONDS.toSeconds(dateDiff)
    val minute: Long = TimeUnit.MILLISECONDS.toMinutes(dateDiff)
    val hour: Long = TimeUnit.MILLISECONDS.toHours(dateDiff)
    val day: Long = TimeUnit.MILLISECONDS.toDays(dateDiff)
    when {
        second < 1 -> {
            this.text = this.context.getString(R.string.string_recently_ago)
        }
        second < 60 -> {
            this.text = "$second ${this.context.getString(R.string.string_seconds)} $suffix"
        }
        minute < 60 -> {
            this.text = "$minute ${this.context.getString(R.string.string_minutes)} $suffix"
        }
        hour < 24 -> {
            this.text = "$hour ${this.context.getString(R.string.string_hour)} $suffix"
        }
        day >= 7 -> {
            this.text = when {
                day > 360 -> {
                    (day / 360).toString() + " ${this.context.getString(R.string.string_year)} " + suffix
                }
                day > 30 -> {
                    (day / 30).toString() + " ${this.context.getString(R.string.string_month)} " + suffix
                }
                else -> {
                    (day / 7).toString() + " ${this.context.getString(R.string.string_week)} " + suffix
                }
            }
        }
        day < 7 -> {
            this.text = "$day ${this.context.getString(R.string.string_day)} $suffix"
        }
    }

}