package com.example.billspliter.usermanagement.service

import com.example.billspliter.usermanagement.UserDAO
import com.example.billspliter.usermanagement.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

const val DEFAULT_ROLE = "USER"

@Service
class UserAuthenticationService(
        private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails =
            userRepository.findByName(username)?.toUserDetails()
                    ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "User [$username] not found")
}

fun UserDAO.toUserDetails(): UserDetails =
        User.builder()
                .username(name)
                .password(password)
                .roles(DEFAULT_ROLE)
                .build()

