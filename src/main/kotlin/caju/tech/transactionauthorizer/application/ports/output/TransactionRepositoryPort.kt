package caju.tech.transactionauthorizer.application.ports.output

import caju.tech.transactionauthorizer.domain.Transaction

interface TransactionRepositoryPort {
    fun save(transaction: Transaction)
}