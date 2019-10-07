package com.example.billspliter.groupmanagement.repository

import com.example.billspliter.groupmanagement.GroupDAO
import org.socialsignin.spring.data.dynamodb.repository.EnableScan
import org.springframework.data.repository.CrudRepository

@EnableScan
interface GroupRepository : CrudRepository<GroupDAO, String> {
    override fun findAll(): List<GroupDAO>
    fun existsByName(name: String): Boolean
}