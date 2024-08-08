package caju.tech.transactionauthorizer

import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.repository.AccountRepository
import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.repository.MerchantRepository
import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.repository.TransactionRepository
import caju.tech.transactionauthorizer.adapter.ports.output.redis.repository.LockRepositoryPort
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

@SpringBootApplication(exclude = [RedisAutoConfiguration::class, RedisRepositoriesAutoConfiguration::class])
@EnableDynamoDBRepositories(basePackageClasses = [AccountRepository::class, TransactionRepository::class, MerchantRepository::class])
@EnableRedisRepositories(basePackageClasses = [LockRepositoryPort::class])
@EnableCaching
class TransactionAuthorizerApplication

fun main(args: Array<String>) {
    runApplication<TransactionAuthorizerApplication>(*args)
}
