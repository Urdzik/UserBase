package com.example.myapplication.database

import android.app.Application
import androidx.lifecycle.LiveData

class DatabaseRepository(application: Application) {

    private val dao = UserDatabase.getInstance(application).userDatabaseDao

    fun getData(): LiveData<List<User>> = dao.getAllUsers()

    fun insertUser(user: User){
        dao.insert(user)
    }
}