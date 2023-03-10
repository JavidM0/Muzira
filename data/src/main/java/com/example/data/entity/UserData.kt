package com.example.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.entity.UserData.Companion.TABLE_NAME
import com.example.domain.entity.User

@Entity(tableName = TABLE_NAME)
data class UserData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val email: String,
    val password: String,
) {
    companion object {
        const val TABLE_NAME = "users_table"
    }
}

internal fun UserData.toDomainEntity() = User(
    id = id,
    email = email,
    password = password,
)

internal fun User.toDataModel() = UserData(
    id = id,
    email = email,
    password = password,
)
