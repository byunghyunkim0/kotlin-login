package com.my.kotlinlogin.service

import com.my.kotlinlogin.domain.user.AuthProvider
import com.my.kotlinlogin.domain.user.Role
import com.my.kotlinlogin.domain.user.User
import com.my.kotlinlogin.domain.user.UserRepository

class UserService(
    private val userRepository: UserRepository
) {

    fun registerUser(
        email: String,
        name: String,
        provider: AuthProvider,
        providerId: String,
    ): User {
        val user = userRepository.findByEmail(email)
        if (user != null) return user

        val newUser = User(
            email = email,
            name = name,
            provider = provider,
            providerId = providerId,
            role = Role.USER
        )
        return userRepository.save(newUser)
    }
}