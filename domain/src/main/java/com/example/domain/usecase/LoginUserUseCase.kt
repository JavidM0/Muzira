package com.example.domain.usecase

import com.example.domain.entity.User
import com.example.domain.repository.UserRepository

class LoginUserUseCase(private val userRepository: UserRepository) {

    suspend fun execute(email: String, password: String): User? {
        return userRepository.loginUser(email, password)
    }
}
