package caju.tech.transactionauthorizer.domain

import java.time.LocalDateTime
import java.util.*

data class Account(
    val accountId: UUID = UUID.randomUUID(),
    val name: String,
    val documentNumber: String,
    var food: Double = 0.0,
    var meal: Double = 0.0,
    var cash: Double = 0.0,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updateAt: LocalDateTime = LocalDateTime.now(),
) {
    fun updateBalance(balanceAccount: BalanceAccount) {
        balanceAccount.food?.let { this.food += it }
        balanceAccount.meal?.let { this.meal += it }
        balanceAccount.cash?.let { this.cash += it }
    }
}