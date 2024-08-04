package caju.tech.transactionauthorizer.application.ports.input

import caju.tech.transactionauthorizer.domain.BalanceAccount

interface UpdateBalanceAccountUseCasePort {
    fun execute(balanceAccount: BalanceAccount)
}