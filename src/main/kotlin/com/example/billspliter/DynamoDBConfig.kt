package com.example.billspliter

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableDynamoDBRepositories("com.example.billspliter")
class DynamoDBConfig(
        @Value("\${amazon.dynamodb.endpoint}")
        private val amazonDynamoDBEndpoint: String,

        @Value("\${aws.access.key.id}")
        private val AWS_ACCESS_KEY: String,

        @Value("\${aws.secret.access.key}")
        private val AWS_SECRET_ACCESS_KEY: String

) {

    @Bean
    fun amazonAWSCredentials(): AWSCredentials = BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_ACCESS_KEY)

    @Bean
    fun amazonDynamoDB(): AmazonDynamoDB {
        val builder = AmazonDynamoDBClientBuilder.standard()

        println ( "DB aws.dynamodb.endpoint = $amazonDynamoDBEndpoint" )

        if (amazonDynamoDBEndpoint.isNotBlank()) {
            println ( "Using local Dynamo DB." )
            builder.withCredentials(AWSStaticCredentialsProvider(amazonAWSCredentials()))
            builder.withEndpointConfiguration(
                    AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, "us-east-1"))
        }

        return builder.build()
    }
}

