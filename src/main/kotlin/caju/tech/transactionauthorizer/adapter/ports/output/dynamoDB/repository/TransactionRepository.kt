package caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.repository

import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.entity.TransactionEntity
import org.socialsignin.spring.data.dynamodb.repository.EnableScan
import org.springframework.data.repository.CrudRepository

@EnableScan
interface TransactionRepository : CrudRepository<TransactionEntity, String>