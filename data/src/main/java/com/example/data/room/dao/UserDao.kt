package com.example.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entity.UserDt
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun registerUser(user: UserDt)

    @Query("SELECT * FROM ${UserDt.TABLE_NAME} WHERE email LIKE :email AND password LIKE :password")
    fun loginUser(email: String, password: String): Flow<List<UserDt>>
}