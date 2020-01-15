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
    application: Application
) : AndroidViewModel(application) {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private var _userData = MutableLiveData<User>()
    val userData: LiveData<User>
        get() = _userData


    fun createNewUser(name: String, age: String, info: String, url: String) {
        uiScope.launch {
            val newUser = User()
            if (name.isNotEmpty() && name != "") newUser.name = name else newUser.name = "No information"
            if (age.isNotEmpty() && age != "") newUser.age = age.toInt() else newUser.age = 1
            if (info.isNotEmpty() && info != "") newUser.info = info else newUser.info = "No information"
            if (url.isNotEmpty() && url != "") newUser.url = url else newUser.url = "data:image/png;base64,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"
            insert(newUser)
            _userData.value = newUser
        }
    }


    fun doneNavigating() {
        _userData.value = null
    }


    private suspend fun insert(newUser: User) {
        withContext(Dispatchers.IO) {
            database.insert(newUser)
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}


