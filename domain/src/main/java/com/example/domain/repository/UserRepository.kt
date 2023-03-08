package com.example.domain.repository

import com.example.data.entity.UserData
import com.example.data.room.dao.UserDao

class UserRepository(private val userDao: UserDao) {

    suspend fun registerUser(userData: UserData) {
        userDao.registerUser(userData)
    }

    suspend fun loginUser(email: String, password: String): UserData? {
        return userDao.loginUser(email, password)
    }
}
