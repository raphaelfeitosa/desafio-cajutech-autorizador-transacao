package caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.entity

import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.util.LocalDateTimeConverter
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted
import org.springframework.data.annotation.Id
import java.time.LocalDateTime

@DynamoDBTable(tableName = "transactions")
data class TransactionEntity(
    @Id
    @DynamoDBHashKey(attributeName = "transaction_id")
    val transactionId: String,

    @DynamoDBAttribute(attributeName = "account_id")
    val accountId: String,

    @DynamoDBAttribute(attributeName = "amount")
    val amount: Double,

    @DynamoDBAttribute(attributeName = "merchant")
    val merchant: String,

    @DynamoDBAttribute(attributeName = "mcc")
    val mcc: String,

    @DynamoDBAttribute(attributeName = "create_at")
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter::class)
    val createAt: LocalDateTime,
)