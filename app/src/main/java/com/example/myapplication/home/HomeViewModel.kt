package com.example.myapplication.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.database.UserDatabaseDao

class HomeViewModel(
    private val database: UserDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    val users = database.getAllUsers()


    private var _buttonAction = MutableLiveData<Boolean>()
    val buttonAction: LiveData<Boolean>
        get() = _buttonAction

    private var _navigationToDetailInfo = MutableLiveData<Long>()
    val navigationToDetailInfo: LiveData<Long>
        get() = _navigationToDetailInfo


    fun onUserClicked(id: Long) {
        _navigationToDetailInfo.value = id
    }
    fun onUserDtailInfoNavigated(){
        _navigationToDetailInfo.value = null
    }


    fun clickButton() {
        _buttonAction.value = true
    }

    fun doneNavigated(){
        _buttonAction.value = null
    }

}
