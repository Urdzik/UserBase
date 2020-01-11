package com.example.myapplication.newdata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.database.User
import com.example.myapplication.database.UserDatabase
import com.example.myapplication.database.UserDatabaseDao
import kotlinx.coroutines.*

class NewInfoViewModel(
    private val database: UserDatabaseDao,
    private var name: String,
    private var age: String,
    private var info: String,
    application: Application
) : AndroidViewModel(application) {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private var _buttonAction = MutableLiveData<User>()
    val buttonAction: LiveData<User>
        get() = _buttonAction


    fun createNewUser() {
        uiScope.launch {
            val newUser = User()
            newUser.name = name
            newUser.age = age
            newUser.url = "url"
            newUser.info = info
            insert(newUser)
            _buttonAction.value = newUser

        }
    }


    private suspend fun insert(newUser: User) {
        withContext(Dispatchers.IO){
            database.insert(newUser)
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}


