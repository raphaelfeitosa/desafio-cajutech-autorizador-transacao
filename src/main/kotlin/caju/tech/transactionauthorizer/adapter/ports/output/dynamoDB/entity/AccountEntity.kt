package caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.entity

import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.util.LocalDateTimeConverter
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted
import org.springframework.data.annotation.Id
import java.time.LocalDateTime

@DynamoDBTable(tableName = "accounts")
data class AccountEntity(
    @Id
    @DynamoDBHashKey(attributeName = "account_id")
    var accountId: String? = null,

    @DynamoDBAttribute(attributeName = "name")
    var name: String? = null,

    @DynamoDBAttribute(attributeName = "document")
    var documentNumber: String? = null,

    @DynamoDBAttribute(attributeName = "food")
    var food: Double? = null,

    @DynamoDBAttribute(attributeName = "meal")
    var meal: Double? = null,

    @DynamoDBAttribute(attributeName = "cash")
    var cash: Double? = null,

    @DynamoDBAttribute(attributeName = "create_at")
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter::class)
    var createAt: LocalDateTime? = null,

    @DynamoDBAttribute(attributeName = "update_at")
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter::class)
    var updateAt: LocalDateTime = LocalDateTime.now()
)