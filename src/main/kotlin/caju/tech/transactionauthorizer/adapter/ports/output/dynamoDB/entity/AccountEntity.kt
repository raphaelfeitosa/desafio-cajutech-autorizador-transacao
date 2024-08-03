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
    val accountId: String,

    @DynamoDBAttribute(attributeName = "name")
    val name: String,

    @DynamoDBAttribute(attributeName = "document")
    val documentNumber: String,

    @DynamoDBAttribute(attributeName = "food")
    var food: Double,

    @DynamoDBAttribute(attributeName = "meal")
    var meal: Double,

    @DynamoDBAttribute(attributeName = "cash")
    var cash: Double,

    @DynamoDBAttribute(attributeName = "create_at")
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter::class)
    val createAt: LocalDateTime,
)