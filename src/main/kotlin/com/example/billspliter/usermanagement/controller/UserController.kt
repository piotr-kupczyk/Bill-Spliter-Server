package com.example.billspliter.usermanagement.controller

import com.example.billspliter.usermanagement.UserDAO
import com.example.billspliter.usermanagement.httpmodel.UserRequest
import com.example.billspliter.usermanagement.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@RequestMapping("/user")
@RestController
class UserController(
        private val service: UserService
) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @GetMapping
    fun getUsers(): ResponseEntity<List<UserDAO>> {
        log.info("GET request for all users")
        return ResponseEntity.ok(service.getUsers())
    }

    @GetMapping("/{userId}")
    fun getUserById(@PathVariable userId: String): ResponseEntity<UserDAO> {
        log.info("GET request for user [$userId]")
        return ResponseEntity.ok(service.getUserById(userId))
    }

    @GetMapping("/friend")
    fun getUserFriends(@RequestParam userId: String): ResponseEntity<List<UserDAO>> {
        log.info("GET request for friends")
        return ResponseEntity.ok(service.getUserFriends(userId))
    }

    @PostMapping
    fun createUser(@RequestBody userRequest: UserRequest): ResponseEntity<UserDAO> {
        log.info("POST request. Creating user: $userRequest")
        return ResponseEntity.ok(service.createUser(userRequest))
    }

    @DeleteMapping
    fun removeUser(@RequestParam userId: String): ResponseEntity<Unit> {
        log.info("DELETE request. Removing user: $userId")
        return ResponseEntity.ok(service.removeUser(userId))
    }

    @PutMapping("/friend")
    fun putUserFriends(
            @RequestParam userId: String,
            @RequestBody newFriendsIds: List<String>
    ): ResponseEntity<UserDAO> {
        log.info("PUT request. Add new friends: $userId")
        return ResponseEntity.ok(service.putUserFriends(userId, newFriendsIds))
    }

}