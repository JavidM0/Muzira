package com.example.data.mapper

import com.example.data.entity.UserDt
import com.example.domain.entity.User

class UserMapper : Mapper<User, UserDt> {
    override fun mapToEntityDt(entity: User): UserDt =
        UserDt(entity.id, entity.email, entity.password)

    override fun mapToEntity(entityDt: UserDt): User =
        User(entityDt.id, entityDt.email, entityDt.password)
}