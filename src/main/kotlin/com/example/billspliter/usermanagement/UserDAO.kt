package com.example.billspliter.usermanagement

import com.amazonaws.services.dynamodbv2.datamodeling.*

//data class DynamoId(
//        @DynamoDBHashKey
//        @DynamoDBAutoGeneratedKey
//
//)

@DynamoDBTable(tableName = "users")
data class UserDAO(
        @DynamoDBHashKey
        @DynamoDBAutoGeneratedKey
        var id: String? = null,
        @DynamoDBAttribute
        var name: String = "",
        @DynamoDBAttribute
        var friends: MutableList<UserDAO> = mutableListOf()
)

data class Spend(
        @DynamoDBHashKey
        @DynamoDBAutoGeneratedKey
        var id: String,
        @DynamoDBAttribute
        var title: String,
        @DynamoDBAttribute
        var value: Double,
        @DynamoDBAttribute
        var currency: String, // TODO future cash change by revolut API
        @DynamoDBAttribute
        var payer: UserDAO,
        @DynamoDBAttribute
        var concerns: Map<UserDAO, Double>
)