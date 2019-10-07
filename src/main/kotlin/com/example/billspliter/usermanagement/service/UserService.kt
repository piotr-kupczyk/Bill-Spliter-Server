package com.example.billspliter.usermanagement.service

import com.example.billspliter.usermanagement.UserDAO
import com.example.billspliter.usermanagement.httpmodel.UserRequest
import com.example.billspliter.usermanagement.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserService(
        private val userRepository: UserRepository
) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    fun getUsers(): List<UserDAO> {
        return userRepository.findAll().toList()
    }

    fun getUsersByIds(ids: List<String>): List<UserDAO> = userRepository.findAllById(ids)

    fun getUserFriends(userId: String): List<UserDAO> =
            userRepository.findByIdOrNull(userId)?.friends ?: listOf(UserDAO()) // TODO throw NotFound

    fun createUser(userRequest: UserRequest): UserDAO {
        return userRepository.save(userRequest.toUserDAO())
    }

    fun putUserFriends(userId: String, newFriendsIds: List<String>): UserDAO =
            with(userRepository) {
                val user = findByIdOrNull(userId)
                val friendsDAO = findAllById( newFriendsIds.filter { it != userId } )
                user?.let {
                    it.friends.addAll(friendsDAO)
                    save(it)
                }?: UserDAO() // TODO throw NotFound
            }

    fun removeUser(userId: String) {
        userRepository.deleteById(userId)
        log.info("Removed user: $userId")
    }


}