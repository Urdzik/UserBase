package com.example.myapplication.home

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.myapplication.database.User
import kotlinx.android.synthetic.main.item_list.view.*


@BindingAdapter("userName")
fun TextView.setUserName(item: User?) {
    item?.let {
        text = item.name
    }

}

@BindingAdapter("userAge")
fun TextView.setUserAge(item: User?) {
    item?.let {
        text = item.age.toString()
    }

}

@BindingAdapter("userInfo")
fun TextView.setUserInfo(item: User?) {
    item?.let {
        text = item.info
    }
}

@BindingAdapter("userPhoto")
fun ImageView.setUserPhoto(item: User?){
    item?.let {
        Log.i("item", item.url)
//       val i = Glide.with(imageView.context).load(item.url).into(imageView)
//        setImageBitmap()
    }
}