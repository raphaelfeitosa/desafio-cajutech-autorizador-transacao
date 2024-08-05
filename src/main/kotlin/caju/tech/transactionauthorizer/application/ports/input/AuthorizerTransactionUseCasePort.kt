package caju.tech.transactionauthorizer.application.ports.input

import caju.tech.transactionauthorizer.domain.Transaction

interface AuthorizerTransactionUseCasePort {
    fun execute(transaction: Transaction): String
}