package caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.config

import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.model.*
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
@EnableDynamoDBRepositories(basePackages = ["caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.repository"])
class DynamoDBConfig {

    @Bean
    fun amazonDynamoDB(): AmazonDynamoDB {
        return AmazonDynamoDBClientBuilder.standard()
            .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration("http://localhost:4566", "us-east-1"))
            .build()
    }

    @Bean
    fun createTables(dynamoDB: AmazonDynamoDB) = CommandLineRunner {

        val requestAccounts = CreateTableRequest()
            .withTableName("accounts")
            .withKeySchema(KeySchemaElement("account_id", KeyType.HASH))
            .withAttributeDefinitions(
                AttributeDefinition("account_id", ScalarAttributeType.S),
            )
            .withProvisionedThroughput(ProvisionedThroughput(5L, 5L))

        val tables = dynamoDB.listTables().tableNames

        if (!tables.contains("accounts")) {
            dynamoDB.createTable(requestAccounts)
        }
    }
}