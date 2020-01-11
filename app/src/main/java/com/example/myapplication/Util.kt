package com.example.myapplication

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import com.example.myapplication.database.User

fun formatNights(nights: List<User>, resources: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply {
        append(resources.getString(R.string.title))
        nights.forEach {
            append("<br>")
            append("name: ")
            append("\t${it.name}<br>")
            append("age: ")
            append("\t${it.age}<br>")
            append("info: ")
            append("\t${it.info}<br>")

        }

        // fromHtml is deprecated for target API without a flag, but since our minSDK is 19, we
        // can't use the newer version, which requires minSDK of 24
        //https://developer.android.com/reference/android/text/Html#fromHtml(java.lang.String,%20int)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            return Html.fromHtml(sb.toString())
        }
    }
}