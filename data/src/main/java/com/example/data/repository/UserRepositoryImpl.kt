package com.example.data.repository

import com.example.data.mapper.UserMapper
import com.example.data.room.dao.UserDao
import com.example.domain.entity.User
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val userDao: UserDao,
    private val userMapper: UserMapper,
) : UserRepository {
    override suspend fun registerUser(user: User) {
        userDao.registerUser(userMapper.mapToEntityDt(user))
    }

    override fun loginUser(email: String, password: String): Flow<List<User>> {
        return userDao.loginUser(email, password).map { listOfUsers ->
            listOfUsers.map { userDt ->
                userMapper.mapToEntity(userDt)
            }
        }
    }
}