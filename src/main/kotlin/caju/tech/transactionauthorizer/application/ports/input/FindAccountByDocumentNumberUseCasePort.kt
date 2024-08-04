package caju.tech.transactionauthorizer.application.ports.input

import caju.tech.transactionauthorizer.domain.Account

interface FindAccountByDocumentNumberUseCasePort {
    fun execute(documentNumber: String): Account
}