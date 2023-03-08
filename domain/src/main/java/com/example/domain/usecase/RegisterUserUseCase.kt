package com.example.domain.usecase

import com.example.data.entity.UserData
import com.example.domain.repository.UserRepository

class RegisterUserUseCase(private val userRepository: UserRepository) {

    suspend fun execute(userData: UserData) {
        userRepository.registerUser(userData)
    }
}
