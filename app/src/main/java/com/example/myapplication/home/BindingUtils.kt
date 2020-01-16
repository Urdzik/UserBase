package com.example.myapplication.home

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.myapplication.R
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
fun setUserPhoto(imgView: ImageView, imgUtl: String?){
    imgUtl?.let {
        val imgUri = imgUtl.toUri().buildUpon().scheme("https").build()
         Glide.with(imgView.context)
             .load(imgUri)
             .error(R.drawable.ic_launcher_background)
             .centerCrop()
             .into(imgView)
    }
}