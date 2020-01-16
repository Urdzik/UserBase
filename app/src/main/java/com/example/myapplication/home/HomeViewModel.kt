package com.example.myapplication.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.database.UserDatabaseDao
import kotlinx.coroutines.*

class HomeViewModel(
    private val database: UserDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    val users = database.getAllUsers()
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


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

    fun onDelete(){
        uiScope.launch {
            delete()
        }
    }

    private suspend fun delete(){
        withContext(Dispatchers.IO){
            database.delete()
        }
    }

    fun clickButton() {
        _buttonAction.value = true
    }

    fun doneNavigated(){
        _buttonAction.value = null
    }

}
