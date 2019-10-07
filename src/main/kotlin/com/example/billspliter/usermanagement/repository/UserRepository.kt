package com.example.billspliter.usermanagement.repository

import com.example.billspliter.usermanagement.UserDAO
import org.socialsignin.spring.data.dynamodb.repository.EnableScan
import org.springframework.data.repository.CrudRepository
import java.util.*

@EnableScan
interface UserRepository: CrudRepository<UserDAO, String> {
    override fun findAllById(ids: Iterable<String>): List<UserDAO>
}

