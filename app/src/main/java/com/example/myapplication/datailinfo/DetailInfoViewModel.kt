package com.example.myapplication.datailinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.database.User
import com.example.myapplication.database.UserDatabaseDao
import kotlinx.coroutines.Job


class DetailInfoViewModel(
    private val userKey: Long = 0L,
    dataSource: UserDatabaseDao) : ViewModel() {

    val database = dataSource

    private val viewModelJob = Job()

    private val user: LiveData<User>

    fun getUser() = user

    init {
        user = database.getUserWithId(userKey)
    }

    private val _navigationToHomeFragment = MutableLiveData<Boolean?>()
    val navigationToHomeFragment: LiveData<Boolean?>
        get() = _navigationToHomeFragment


    fun doneNavigated() {
        _navigationToHomeFragment.value = null
    }

    fun onClose() {
        _navigationToHomeFragment.value = true
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}
