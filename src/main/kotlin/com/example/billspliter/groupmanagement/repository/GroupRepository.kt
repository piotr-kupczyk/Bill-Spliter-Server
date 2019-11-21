package com.example.billspliter.groupmanagement.repository

import com.example.billspliter.groupmanagement.GroupDAO
import org.socialsignin.spring.data.dynamodb.repository.EnableScan
import org.socialsignin.spring.data.dynamodb.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

@EnableScan
interface GroupRepository : CrudRepository<GroupDAO, String> {
    override fun findAll(): List<GroupDAO>
    override fun findById(id: String): Optional<GroupDAO>
//    override fun findById(id: String): GroupDAO?
    fun existsByName(name: String): Boolean
}