package com.example.domain.usecase

import com.example.data.entity.UserData
import com.example.domain.repository.UserRepository

class LoginUserUseCase(private val userRepository: UserRepository) {

    suspend fun execute(email: String, password: String): UserData? {
        return userRepository.loginUser(email, password)
    }
}
