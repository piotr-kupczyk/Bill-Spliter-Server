package com.example.billspliter.groupmanagement.httpmodel

import com.example.billspliter.groupmanagement.SpendDAO
import java.sql.Timestamp

data class SpendRequest(
        val title: String,
        val value: Double,
        val payerId: String,
        val concerns: List<String>
)