package caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.service

import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.converter.toEntity
import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.repository.TransactionRepository
import caju.tech.transactionauthorizer.application.ports.output.TransactionRepositoryPort
import caju.tech.transactionauthorizer.domain.Transaction
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class TransactionRepositoryService(
    private val transactionRepository: TransactionRepository,
) : TransactionRepositoryPort {

    companion object {
        private val logger = LoggerFactory.getLogger(TransactionRepositoryService::class.java.name)
    }

    @Transactional
    override fun save(transaction: Transaction) {
        logger.info("Starting process to save a new transaction: [{}], in DB.", transaction)
        val transactionEntity = transaction.toEntity()
        transactionRepository.save(transactionEntity)
        logger.info("Done process to save a new transaction: [{}], in DB", transaction)
    }

}