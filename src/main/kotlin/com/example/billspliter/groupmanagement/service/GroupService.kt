package com.example.billspliter.groupmanagement.service

import com.example.billspliter.groupmanagement.GroupDAO
import com.example.billspliter.groupmanagement.MemberHistory
import com.example.billspliter.groupmanagement.SpendDAO
import com.example.billspliter.groupmanagement.httpmodel.GroupRequest
import com.example.billspliter.groupmanagement.httpmodel.SpendRequest
import com.example.billspliter.groupmanagement.repository.GroupRepository
import com.example.billspliter.usermanagement.Spend
import com.example.billspliter.usermanagement.service.UserService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.net.URLEncoder
import java.sql.Timestamp
import java.util.*

@Service
class GroupService(
        val repository: GroupRepository,
        val userService: UserService
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

    fun addSpend(groupId: String, spendRequest: SpendRequest): SpendDAO =
            repository.findByIdOrNull(groupId)?.let { group ->
                val user = userService.getUserById(spendRequest.payer)?.takeIf { user -> user.groups.any { it.id == groupId } } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User [${spendRequest.payer}] doesn't belong to group: [$groupId]")
                val spend = spendRequest.toSpendDAO(user.name)
                group.members.find { it.userId == user.id }!!.spends.add(spend)
                repository.save(group)
                spend
            } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't find group with id: [$groupId]")
}

fun SpendRequest.toSpendDAO(userName: String): SpendDAO {
    val imageURL = "https://ui-avatars.com/api/?name=$userName&size=64&color=FFFFF&background=007AFF"
    val encodedURL = URLEncoder.encode(imageURL, "UTF-8")
            .replace(' ', '+')
    return SpendDAO(title, Timestamp(date), value, encodedURL)
}