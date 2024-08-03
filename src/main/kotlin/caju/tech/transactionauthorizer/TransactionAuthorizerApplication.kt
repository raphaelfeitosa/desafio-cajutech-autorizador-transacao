package caju.tech.transactionauthorizer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TransactionAuthorizerApplication

fun main(args: Array<String>) {
	runApplication<TransactionAuthorizerApplication>(*args)
}
