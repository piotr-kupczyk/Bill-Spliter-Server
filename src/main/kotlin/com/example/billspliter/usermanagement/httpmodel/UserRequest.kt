package com.example.billspliter.usermanagement.httpmodel

import com.example.billspliter.usermanagement.UserDAO

data class UserRequest(
        val name: String
) {
    fun toUserDAO(): UserDAO =
            UserDAO(
                    name = name,
                    groups = mutableListOf()
            )
}