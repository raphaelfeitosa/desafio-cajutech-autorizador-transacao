package caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.util

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeConverter : DynamoDBTypeConverter<String, LocalDateTime> {

    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    override fun convert(localDateTime: LocalDateTime): String {
        return localDateTime.format(formatter)
    }

    override fun unconvert(string: String): LocalDateTime {
        return LocalDateTime.parse(string, formatter)
    }
}