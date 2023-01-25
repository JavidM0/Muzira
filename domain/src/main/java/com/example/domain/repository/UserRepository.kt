package com.example.domain.repository

import com.example.domain.entity.User

interface UserRepository {

    suspend fun registerUser(user: User)
    suspend fun loginUser(email: String, password: String): User?
}
