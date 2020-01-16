package com.example.myapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_tablet")
data class User(

    @PrimaryKey(autoGenerate = true)
    var userId: Long = 0L,

    @ColumnInfo(name = "user_name")
    var name: String = "name",

    @ColumnInfo(name = "user_age")
    var age: Int = 0,

    @ColumnInfo(name = "detail_user_info")
    var info: String = "info"
)