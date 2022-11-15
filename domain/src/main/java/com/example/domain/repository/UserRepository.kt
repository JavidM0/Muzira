package com.example.domain.repository

import com.example.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun registerUser(user: User)
    fun loginUser(email: String, password: String): Flow<List<User>>
}