package caju.tech.transactionauthorizer.application.ports.input

import caju.tech.transactionauthorizer.domain.Account

interface CreateAccountUseCasePort {
    fun execute(account: Account): Account
}