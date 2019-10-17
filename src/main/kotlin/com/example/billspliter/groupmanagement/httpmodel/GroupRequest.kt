package com.example.billspliter.groupmanagement.httpmodel

data class GroupRequest(
        val name: String,
        val imageURL: String,
        val membersIds: List<String>
)