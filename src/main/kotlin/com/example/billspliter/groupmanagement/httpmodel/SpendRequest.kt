package com.example.billspliter.groupmanagement.httpmodel

import com.example.billspliter.groupmanagement.SpendDAO
import java.sql.Timestamp

data class SpendRequest(
        val title: String,
        val date: Long,
        val value: Double,
        val payer: String
)