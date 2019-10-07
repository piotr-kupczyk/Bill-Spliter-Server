package com.example.billspliter.groupmanagement.httpmodel

import com.example.billspliter.groupmanagement.GroupDAO
import com.example.billspliter.usermanagement.Spend
import com.example.billspliter.usermanagement.UserDAO

data class GroupRequest(
        val name: String,
        val imageURL: String,
        val membersIds: List<String>
)