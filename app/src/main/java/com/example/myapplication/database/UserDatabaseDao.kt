package com.example.myapplication.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDatabaseDao{

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Query("SELECT * from user_tablet WHERE userId = :key")
    fun get(key: Long): User

    @Query("DELETE FROM user_tablet")
    fun delete()

    @Query("SELECT * FROM user_tablet ORDER BY userId DESC LIMIT 1")
    fun getUser(): User

    @Query("SELECT * FROM user_tablet ORDER BY userId DESC")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM user_tablet WHERE userId = :key ")
    fun getUserWithId(key: Long): LiveData<User>



}