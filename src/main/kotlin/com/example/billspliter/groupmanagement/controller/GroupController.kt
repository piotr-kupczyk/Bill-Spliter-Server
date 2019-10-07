package com.example.billspliter.groupmanagement.controller

import com.example.billspliter.groupmanagement.GroupDAO
import com.example.billspliter.groupmanagement.httpmodel.GroupRequest
import com.example.billspliter.groupmanagement.service.GroupService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/group")
@RestController
class GroupController(
    val groupService: GroupService
) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @GetMapping
    fun getGroups(): ResponseEntity<List<GroupDAO>> {
        log.info("GET request for all groups")
        return ResponseEntity.ok(groupService.getGroups())
    }

    @PostMapping
    fun createGroup(@RequestBody group: GroupRequest): ResponseEntity<GroupDAO> {
        log.info("POST request. Creating ")
        return ResponseEntity.ok(groupService.createGroup(group))
    }
}