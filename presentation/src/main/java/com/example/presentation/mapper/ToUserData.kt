package com.example.presentation.mapper

import com.example.data.entity.UserData
import com.example.presentation.model.User

internal fun User.toUserData() = UserData(
    id = id,
    email = email,
    password = password
)
