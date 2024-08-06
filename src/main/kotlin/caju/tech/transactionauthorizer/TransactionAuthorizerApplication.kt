package caju.tech.transactionauthorizer

import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.repository.AccountRepository
import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.repository.TransactionRepository
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableDynamoDBRepositories(basePackageClasses = [AccountRepository::class, TransactionRepository::class])
@EnableCaching
class TransactionAuthorizerApplication

fun main(args: Array<String>) {
    runApplication<TransactionAuthorizerApplication>(*args)
}
