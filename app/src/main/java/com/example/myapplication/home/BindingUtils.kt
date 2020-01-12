package com.example.myapplication.home

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.myapplication.database.User

@BindingAdapter("userName")
fun TextView.setUserName(item: User) {
    text = item.name
}

@BindingAdapter("userAge")
fun TextView.setUserAge(item: User) {
    text = item.age.toString()
}

@BindingAdapter("userInfo")
fun TextView.setUserInfo(item: User) {
    text = item.info
}