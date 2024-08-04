package caju.tech.transactionauthorizer.application.ports.output

import caju.tech.transactionauthorizer.domain.Account

interface AccountRepositoryPort {
    fun save(account: Account)
    fun findByAccountId(accountId: String): Account
}