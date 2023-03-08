package com.example.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.entity.UserData.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class UserData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val email: String,
    val password: String
) {
    companion object {
        const val TABLE_NAME = "users_table"
    }
}
