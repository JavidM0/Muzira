package com.example.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.entity.UserDt
import com.example.data.room.dao.UserDao

@Database(entities = [UserDt::class], version = 1, exportSchema = true)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}