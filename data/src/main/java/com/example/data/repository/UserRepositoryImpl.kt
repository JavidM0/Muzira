package com.example.data.repository

import com.example.data.entity.toDataModel
import com.example.data.entity.toDomainEntity
import com.example.data.room.dao.UserDao
import com.example.domain.entity.User
import com.example.domain.repository.UserRepository

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {

    override suspend fun registerUser(user: User) {
        userDao.registerUser(user.toDataModel())
    }

    override suspend fun loginUser(email: String, password: String): User? {
        return userDao.loginUser(email, password)?.toDomainEntity()
    }
}
