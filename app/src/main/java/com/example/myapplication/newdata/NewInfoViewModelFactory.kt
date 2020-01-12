package com.example.myapplication.newdata

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.database.UserDatabaseDao
import com.example.myapplication.home.HomeViewModel
import java.lang.IllegalArgumentException

class NewInfoViewModelFactory (private val dataSource: UserDatabaseDao,
                            private val application: Application
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewInfoViewModel::class.java)){
            return NewInfoViewModel(dataSource, application)  as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}