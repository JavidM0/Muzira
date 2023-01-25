package com.example.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entity.UserData

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun registerUser(user: UserData)

    @Query("SELECT * FROM ${UserData.TABLE_NAME} WHERE email LIKE :email AND password LIKE :password")
    suspend fun loginUser(email: String, password: String): UserData?
}
