package com.example.myapplication.newdata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.database.DatabaseRepository
import com.example.myapplication.database.User
import com.example.myapplication.database.UserDatabase
import com.example.myapplication.database.UserDatabaseDao
import kotlinx.coroutines.*

class NewInfoViewModel(application: Application) : AndroidViewModel(application) {

    private val databaseRepository = DatabaseRepository(application)

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private var _buttonAction = MutableLiveData<User>()
    val buttonAction: LiveData<User>
        get() = _buttonAction


    fun insertNewUser(user: User) {
        uiScope.launch {
            insert(user)
            _buttonAction.value = user
        }
    }

    private suspend fun insert(newUser: User) {
        withContext(Dispatchers.IO){
            databaseRepository.insertUser(newUser)
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}


