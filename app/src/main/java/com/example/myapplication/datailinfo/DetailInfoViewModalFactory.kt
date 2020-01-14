package com.example.myapplication.datailinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.database.UserDatabaseDao
import java.lang.IllegalArgumentException

class DetailInfoViewModalFactory (
    private val userKey: Long = 0L,
    private val dataSource: UserDatabaseDao): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailInfoViewModel::class.java)){
             return DetailInfoViewModel(userKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}