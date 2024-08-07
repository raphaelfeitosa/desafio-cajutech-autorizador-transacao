package caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.entity

import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.util.LocalDateTimeConverter
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted
import org.springframework.data.annotation.Id
import java.time.LocalDateTime

@DynamoDBTable(tableName = "merchants")
data class MerchantEntity(
    @Id
    @DynamoDBHashKey(attributeName = "merchant_id")
    var merchantId: String? = null,

    @DynamoDBAttribute(attributeName = "name")
    var name: String? = null,

    @DynamoDBAttribute(attributeName = "document")
    var categories: Set<String>? = null,

    @DynamoDBAttribute(attributeName = "create_at")
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter::class)
    var createAt: LocalDateTime? = null,

    @DynamoDBAttribute(attributeName = "update_at")
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter::class)
    var updateAt: LocalDateTime = LocalDateTime.now()
)