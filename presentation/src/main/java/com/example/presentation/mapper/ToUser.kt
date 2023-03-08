package com.example.presentation.mapper

import com.example.data.entity.UserData
import com.example.presentation.model.User

internal fun UserData.toUser() = User(
    id = id,
    email = email,
    password = password
)
