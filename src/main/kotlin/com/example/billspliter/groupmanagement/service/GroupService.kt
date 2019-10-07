package com.example.billspliter.groupmanagement.service

import com.example.billspliter.groupmanagement.GroupDAO
import com.example.billspliter.groupmanagement.MemberHistory
import com.example.billspliter.groupmanagement.httpmodel.GroupRequest
import com.example.billspliter.groupmanagement.repository.GroupRepository
import com.example.billspliter.usermanagement.Spend
import com.example.billspliter.usermanagement.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class GroupService(
        val repository: GroupRepository
) {
    fun getGroups(): List<GroupDAO> = repository.findAll()

    fun createGroup(groupRequest: GroupRequest): GroupDAO =
            with(repository) {
                if (existsByName(groupRequest.name))
                    throw ResponseStatusException(HttpStatus.CONFLICT, "Exists group with provided name")

                val members = groupRequest.membersIds
                        .map { MemberHistory(it) }
                        .toMutableList()

                groupRequest.run {
                    GroupDAO(
                            name = name,
                            imageURL = imageURL,
                            members = members
                    )
                }.also { save(it) }
            }
}