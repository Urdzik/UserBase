package com.example.myapplication.newdata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.database.User
import com.example.myapplication.database.UserDatabaseDao
import kotlinx.coroutines.*

class NewInfoViewModel(
    private val database: UserDatabaseDao,
    application: Application) : AndroidViewModel(application) {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private var _userData = MutableLiveData<User>()
    val userData: LiveData<User>
        get() = _userData


    fun createNewUser(name: String, age: Int, info: String) {
        uiScope.launch {
            val newUser = User()
            newUser.name = name
            newUser.age = age
            newUser.url = "url"
            newUser.info = info
            insert(newUser)
            _userData.value = newUser
        }
    }



    fun doneNavigating(){
        _userData.value = null
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


