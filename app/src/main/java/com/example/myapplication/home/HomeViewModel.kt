package com.example.myapplication.home

import android.app.Application
import androidx.databinding.BindingAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.myapplication.database.User
import com.example.myapplication.database.UserDatabaseDao
import com.example.myapplication.formatNights
import kotlinx.coroutines.*

class HomeViewModel(
    private val database: UserDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private val users = database.getAllUsers()

    val userString = Transformations.map(users){users->
        formatNights(users, application.resources)
    }

    private var _buttonAction = MutableLiveData<Boolean>()
    val buttonAction: LiveData<Boolean>
        get() = _buttonAction



    fun clickButton(){
        _buttonAction.value  = true
    }

}
